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

@InteractionClass(name = "HLAinteractionRoot.RepairComplete")
public class RepairComplete {
    @Parameter(name = "ReceivingObject", coder = HLAunicodeStringCoder.class)
    private String receivingObject;

    @Parameter(name = "RepairingObject", coder = HLAunicodeStringCoder.class)
    private String repairingObject;

    @Parameter(name = "PartRepaired", coder = VehiclePartTypeCoder.class)
    private VehiclePartType partRepaired;

    public RepairComplete() {
        receivingObject = "";
        repairingObject = "";
        partRepaired = null;
    }

    public RepairComplete(String receivingObject, String repairingObject, VehiclePartType partRepaired) {
        this.receivingObject = receivingObject;
        this.repairingObject = repairingObject;
        this.partRepaired = partRepaired;
    }

    public String getReceivingObject() {
        return receivingObject;
    }

    public void setReceivingObject(String receivingObject) {
        this.receivingObject = receivingObject;
    }

    public String getRepairingObject() {
        return repairingObject;
    }

    public void setRepairingObject(String repairingObject) {
        this.repairingObject = repairingObject;
    }

    public VehiclePartType getPartRepaired() {
        return partRepaired;
    }

    public void setPartRepaired(VehiclePartType partRepaired) {
        this.partRepaired = partRepaired;
    }
}
