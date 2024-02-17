package org.example.entity;

import java.math.BigDecimal;

public record RoomType(Integer id,
                       String typeName,
                       Integer occupancy,
                       BigDecimal price) {

}
