package hu.wbstr.mentoringgateway.ratingservice;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import traning.ratingservice.grpcgateway.RateRequest;
import traning.ratingservice.grpcgateway.RateResponse;
import traning.ratingservice.grpcgateway.RatingServiceGrpc.RatingServiceImplBase;

@GrpcService
public class RatingServiceGateway extends RatingServiceImplBase {

    @Override
    public void rate(RateRequest request, StreamObserver<RateResponse> responseObserver) {
        var response = RateResponse
                .newBuilder()
                .setStarts(request.getStarts())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
