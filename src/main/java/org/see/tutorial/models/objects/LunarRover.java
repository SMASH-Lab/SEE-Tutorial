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

import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.see.skf.annotations.ObjectClass;
import org.see.tutorial.RoverFederate;
import org.see.tutorial.models.interactions.RepairRequest;
import org.see.tutorial.models.interactions.RepairResponse;
import org.see.tutorial.types.RepairResponseResultType;
import org.see.tutorial.types.SpaceTimeCoordinateState;
import org.see.tutorial.types.VehiclePartType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@ObjectClass(name = "HLAobjectRoot.PhysicalEntity")
public class LunarRover extends PhysicalEntity {
    private static final Logger logger = LoggerFactory.getLogger(LunarRover.class);
    private static final Vector3D POSITION_INCREMENT = Vector3D.of(10, 0, 0);

    private final RoverFederate federate;
    private final Random rng;
    private final int RNG_THRESHOLD;

    public LunarRover(RoverFederate federate, String name, String status, String type, String parentReferenceFrame, Vector3D spawnPoint) {
        this.federate = federate;
        setName(name);
        setStatus(status);
        setType(type);
        setParentReferenceFrame(parentReferenceFrame);

        SpaceTimeCoordinateState roverState = getState();
        roverState.setPosition(spawnPoint);

        rng = new Random();
        RNG_THRESHOLD = 50;
    }

    public void move() {
        if (!(getStatus().equals("Disrepair"))) {
            SpaceTimeCoordinateState state = getState();
            Vector3D currentPosition = state.getPosition();
            state.setPosition(currentPosition.add(POSITION_INCREMENT));
            currentPosition = state.getPosition();
            logger.info("Rover has moved to: {}", currentPosition);

            causeBreakdown();
        }
    }

    /**
     * Uses a random number generator to simulate the rover having a breakdown and needing maintenance.
     */
    private void causeBreakdown() {
        int num1 = rng.nextInt(30);
        int num2 = rng.nextInt(30);
        int randSum = num1 + num2;

        if (randSum >= RNG_THRESHOLD) {
            // Update the PhysicalEntity status attribute of the rover object instance to "Disrepair".
            setStatus("Disrepair");
            dispatchRepairRequest();
        }
    }

    /**
     * Send out an interaction, essentially a message, requesting repairs to be performed on the lunar rover.
     */
    private void dispatchRepairRequest() {
        VehiclePartType damagedPart = chooseRandomPart();
        RepairRequest request = new RepairRequest(getName(), "repair_vehicle", damagedPart);

        logger.info("{} has fallen into disrepair due to a failure with the {} part and needs maintenance.", getName(), damagedPart.name());
        try {
            federate.sendInteraction(request);
        } catch (Exception e) {
            logger.error("Failed to dispatch repair request", e);
        }
    }

    /**
     * Randomly choose a part.
     * @return A suitable vehicle part.
     */
    private VehiclePartType chooseRandomPart() {
        short num = (short) rng.nextInt(5);
        return VehiclePartType.fromValue(num);
    }

    public void repairCompleted() {
        // Since the repair is complete, let's set the rover's (PhysicalEntity) status attribute back to operational.
        setStatus("Operational");
        logger.info("{} has just been repaired and is back in action!", getName());

        // The federation execution must be made aware of the change in the rover's state i.e., it's been fixed.
        federate.updateObjectInstance(this);

        RepairResponse repairResponse = new RepairResponse(getName(), "repair_vehicle", RepairResponseResultType.REPAIR_ENDED);
        try {
            federate.sendInteraction(repairResponse);
        } catch (Exception e) {
            logger.error("Failed to send repair response interaction", e);
        }
    }
}
