package com.gabrielfernandes.Desafio_SoftLine.models.address;

public final class AddressMapper {
    private AddressMapper(){}

    public static AddressModel toEntity(AddressRequestDTO request){
        AddressModel model = new AddressModel();
        model.setZipCode(request.getZipCode());
        model.setStreet(request.getStreet());
        model.setNumber(request.getNumber());
        model.setNeighborhood(request.getNeighborhood());
        model.setCity(request.getCity());
        model.setState(request.getState());
        model.setCountry(request.getCountry());
        return model;
    }

    public static AddressResponseDTO toResponse(AddressModel model){
        return new AddressResponseDTO(
            model.getZipCode(),
            model.getStreet(),
            model.getNumber(),
            model.getNeighborhood(),
            model.getCity(),
            model.getState(),
            model.getCountry()
        );
    }

    public static void update(AddressModel model, AddressRequestDTO request){
        model.setZipCode(request.getZipCode());
        model.setStreet(request.getStreet());
        model.setNumber(request.getNumber());
        model.setNeighborhood(request.getNeighborhood());
        model.setCity(request.getCity());
        model.setState(request.getState());
        model.setCountry(request.getCountry());
    }
}
