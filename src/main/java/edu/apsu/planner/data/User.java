package edu.apsu.planner.data;

import java.io.Serializable;

public record User(String username, String password) implements Serializable { }
