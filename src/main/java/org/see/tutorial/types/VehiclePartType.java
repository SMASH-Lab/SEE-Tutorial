/*****************************************************************
 SEE Tutorial - Tutorial projects demonstrating how to use the
 SEE HLA Starter Kit Framework.
 Copyright (c) 2026, Hridyanshu Aatreya - Modelling & Simulation
 Group (MSG) at Brunel University of London. All rights reserved.

 GNU Lesser General Public License (GNU LGPL).

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 3.0 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library.
 If not, see http://http://www.gnu.org/licenses/
 *****************************************************************/

package org.see.tutorial.types;

public enum VehiclePartType {
    SENSORS ((short) 0),
    BATTERY ((short) 1),
    TRACK ((short) 2),
    ELECTRONIC_SYSTEMS ((short) 3),
    RADIOS ((short) 4);

    private final short value;

    VehiclePartType(short value) {
        this.value = value;
    }

    public static VehiclePartType fromValue(short value) {
        for (VehiclePartType part : VehiclePartType.values()) {
            if (part.value == value) {
                return part;
            }
        }

        return null;
    }

    public short getValue() {
        return value;
    }
}
