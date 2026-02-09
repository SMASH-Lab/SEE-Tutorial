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

package org.see.tutorial.models.interactions;

import org.see.skf.annotations.InteractionClass;
import org.see.skf.annotations.Parameter;
import org.see.skf.util.encoding.HLAunicodeStringCoder;
import org.see.tutorial.encoding.VehiclePartTypeCoder;
import org.see.tutorial.types.VehiclePartType;

@InteractionClass(name = "HLAinteractionRoot.RepairRequest")
public class RepairRequest {
    @Parameter(name = "ReceivingObject", coder = HLAunicodeStringCoder.class)
    private String receivingObject;

    @Parameter(name = "ServicingObject", coder = HLAunicodeStringCoder.class)
    private String servicingObject;

    @Parameter(name = "RepairType", coder = VehiclePartTypeCoder.class)
    private VehiclePartType repairType;

    public RepairRequest() {
        receivingObject = "";
        servicingObject = "";
        repairType = null;
    }

    public RepairRequest(String receivingObject, String servicingObject, VehiclePartType repairType) {
        this.receivingObject = receivingObject;
        this.servicingObject = servicingObject;
        this.repairType = repairType;
    }

    public String getReceivingObject() {
        return receivingObject;
    }

    public void setReceivingObject(String receivingObject) {
        this.receivingObject = receivingObject;
    }

    public String getServicingObject() {
        return servicingObject;
    }

    public void setServicingObject(String servicingObject) {
        this.servicingObject = servicingObject;
    }

    public VehiclePartType getRepairType() {
        return repairType;
    }

    public void setRepairType(VehiclePartType repairType) {
        this.repairType = repairType;
    }
}
