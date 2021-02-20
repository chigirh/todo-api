package chigirh.app.todo.be.sampleapi.web.protobuf

import chigirh.app.todo.be.sample.grpc.model.Sample
import chigirh.app.todo.be.sample.grpc.service.*
import chigirh.app.todo.be.sampleapi.application.usecase.CalculationUsecase
import chigirh.app.todo.be.sampleapi.domain.model.CalculationEntity
import chigirh.app.todo.be.todoapi.domain.exception.InvalidArgumentException
import com.google.protobuf.Empty
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import org.lognet.springboot.grpc.GRpcService
import chigirh.app.todo.be.sample.grpc.service.Operation as GrpcOperation
import chigirh.app.todo.be.sampleapi.domain.model.Operation as DomainOperation

@GRpcService
class SampleGrpcController(
    val calculationUsecase: CalculationUsecase
) : SampleServiceGrpc.SampleServiceImplBase() {
    /**
     * Unary RPC.
     */
    override fun sampleUnaryMethod(
        request: SampleRequest,
        responseObserver: StreamObserver<SampleResponse>
    ) {
        val res = request.sample.let {
            SampleResponse.newBuilder().setSample(
                Sample.newBuilder()
                    .setSampleId(it.sampleId)
                    .addAllSampleIds(it.sampleIdsList)
                    .setTimestamp(Timestamp.newBuilder().setSeconds(Date().time))
                    .setSampleNum(it.sampleNum)
                    .setSampleBool(it.sampleBool)
                    .setSampleType(it.sampleType)
            ).build()
        }
        responseObserver.onNext(res)
        responseObserver.onCompleted()

    }

    /**
     * Server streaming RPC.
     */
    override fun sampleServerStreamingMethod(
        request: Empty,
        responseObserver: StreamObserver<ServerStreamingResponse>
    ) {
        //2秒間隔でレスポンスを返却
        val executer = Timer()
        var cnt = 0
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                val res = ServerStreamingResponse.newBuilder()
                    .setTimestamp(Timestamp.newBuilder().setSeconds(Date().time))
                    .build()

                responseObserver.onNext(res)
                if (cnt == 9) {
                    executer.cancel();
                    responseObserver.onCompleted();
                }
                cnt++;
            }
        }

        executer.scheduleAtFixedRate(task, 1000, 2000)
    }

    /**
     * Client streaming RPC.
     */
    override fun sampleClientStreamingMethod(responseObserver: StreamObserver<ClientStreamingResponse>): StreamObserver<ClientStreamingRequest> {
        val calculationList = mutableListOf<CalculationEntity>()
        //計算結果をクライアントから受け取り最後にまとめて返却
        return object : StreamObserver<ClientStreamingRequest> {
            override fun onNext(request: ClientStreamingRequest) {
                calculationList.add(
                    CalculationEntity(
                        num = request.num,
                        operation = operationValueOf(request.operator)
                    )
                )
            }

            override fun onError(t: Throwable) {
            }

            override fun onCompleted() {
                val res = ClientStreamingResponse.newBuilder()
                    .setTotal(calculationUsecase(calculationList))
                    .build()
                responseObserver.onNext(res)
                responseObserver.onCompleted()
            }
        }
    }

    /**
     * Bidirectional streaming RPC.
     */
    override fun sampleBidirectionalStreamingMethod(
        responseObserver: StreamObserver<ClientStreamingResponse>
    ): StreamObserver<ClientStreamingRequest> {
        val calculationList = mutableListOf<CalculationEntity>()
        //計算結果をクライアントから受け取り最後にまとめて返却
        //10秒間隔で現在の計算結果を返却
        val executer = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                val res = ClientStreamingResponse.newBuilder()
                    .setTotal(calculationUsecase(calculationList))
                    .build()
                responseObserver.onNext(res)
            }
        }

        executer.scheduleAtFixedRate(task, 1000, 10000)

        return object : StreamObserver<ClientStreamingRequest> {
            override fun onNext(request: ClientStreamingRequest) {
                calculationList.add(
                    CalculationEntity(
                        num = request.num,
                        operation = operationValueOf(request.operator)
                    )
                )
            }

            override fun onError(t: Throwable) {
            }

            override fun onCompleted() {
                executer.cancel()
                val res = ClientStreamingResponse.newBuilder()
                    .setTotal(calculationUsecase(calculationList))
                    .build()
                responseObserver.onNext(res)
                responseObserver.onCompleted()
            }
        }
    }

    private fun operationValueOf(operation: GrpcOperation) = when (operation) {
        GrpcOperation.ADD -> {
            DomainOperation.ADD
        }
        GrpcOperation.SUB -> {
            DomainOperation.SUB
        }
        GrpcOperation.MUL -> {
            DomainOperation.MUL
        }
        GrpcOperation.DIV -> {
            DomainOperation.DIV
        }
        GrpcOperation.UNRECOGNIZED -> {
            //デッドロジック
            throw InvalidArgumentException("operation")
        }
    }
}