package com.learn.reservation.model;

import lombok.Data;

@Data
public class RoomResponse {
    private Long id;
    private Long roomNumber;
    private String roomStatus;
    private String roomType;
}
