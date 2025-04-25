package service;

import utils.Move;
import model.Refrigerator;

import java.io.IOException;

public interface RefrigeratorService {
    Refrigerator loadRefrigerator(String fileName) throws IOException;
    Move play() throws CloneNotSupportedException;

}
