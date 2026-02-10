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

package org.see.tutorial.models.objects;

import org.see.skf.annotations.ObjectClass;
import org.see.tutorial.RepairFederate;
import org.see.tutorial.models.interactions.RepairComplete;
import org.see.tutorial.models.interactions.RepairRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;

@ObjectClass(name = "HLAobjectRoot.PhysicalEntity")
public class RepairVehicle extends PhysicalEntity {
    private static final Logger logger = LoggerFactory.getLogger(RepairVehicle.class);

    private final RepairFederate federate;

    // This kind of array list is specialized for concurrency operations. A regular ArrayList would give us problems
    // because it isn't designed for such cases. We know that the list will most certainly be modified by listeners
    // that are situated in different threads. Consequently, they might try to perform read/write operations at the
    // same time.
    private final CopyOnWriteArrayList<RepairRequest> repairRequests;

    public RepairVehicle(RepairFederate federate, String name) {
        setName(name);
        this.federate = federate;
        repairRequests = new CopyOnWriteArrayList<>();
    }

    public void process() {
        for (RepairRequest repairRequest: repairRequests) {
            // Once a repair is complete, let the rover federate know and remove the request from the list - it's complete.
            RepairComplete repairComplete = new RepairComplete(repairRequest.getReceivingObject(), repairRequest.getServicingObject(), repairRequest.getRepairType());
            dispatchRepairCompleteInteraction(repairComplete);

            repairRequests.remove(repairRequest);
        }
    }

    public void addRepairRequest(RepairRequest repairRequest) {
        repairRequests.add(repairRequest);
    }

    private void dispatchRepairCompleteInteraction(RepairComplete repairComplete) {
        logger.info("{} part repairs completed for {}.", repairComplete.getPartRepaired(), repairComplete.getReceivingObject());
        try {
            federate.sendInteraction(repairComplete);
        } catch (Exception e) {
            logger.error("Failed to send repair complete interaction.", e);
        }
    }
}
