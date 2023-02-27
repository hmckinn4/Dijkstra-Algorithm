package com.solvd.fastestalgo.service;

import com.solvd.fastestalgo.binary.User;

public interface UserService {

    User logUser(String name, String startingTown, String destinationTown, String path);
}
