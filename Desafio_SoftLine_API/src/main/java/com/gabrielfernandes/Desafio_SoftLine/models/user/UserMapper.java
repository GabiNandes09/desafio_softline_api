package com.gabrielfernandes.Desafio_SoftLine.models.user;

public final class UserMapper {
    private UserMapper(){}

    public static UserModel toEntity(UserRequestDTO request){
        UserModel model = new UserModel();

        model.setUsername(request.getUsername());
        model.setPassword(request.getPassword());

        return model;
    }

    public static UserResponseDTO toResponse(UserModel model){
        return new UserResponseDTO(
            model.getId(),
            model.getUsername()
        );
    }

    public static void upgrade(UserModel model, UserRequestDTO request){
        model.setUsername(request.getUsername());
        model.setPassword(request.getPassword());
    }
}
