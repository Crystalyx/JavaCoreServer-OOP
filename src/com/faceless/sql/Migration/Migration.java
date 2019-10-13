package com.faceless.sql.Migration;

public interface Migration
{
    void up(); // Change DB for new program version

    void down(); // Cancel changes
}
