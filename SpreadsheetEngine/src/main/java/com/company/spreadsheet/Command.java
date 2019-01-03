package com.company.spreadsheet;

public interface Command {
    void runCommand(String[] input) throws Exception;
}
