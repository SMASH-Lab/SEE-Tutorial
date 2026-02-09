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

package org.see.tutorial.encoding;

import hla.rti1516_2025.encoding.DecoderException;
import hla.rti1516_2025.encoding.EncoderFactory;
import hla.rti1516_2025.encoding.HLAinteger16BE;
import org.see.skf.core.Coder;
import org.see.skf.core.HLAUtilityFactory;
import org.see.tutorial.types.RepairResponseResultType;

public class RepairResponseResultTypeCoder implements Coder<RepairResponseResultType> {
    private final HLAinteger16BE coder;

    public RepairResponseResultTypeCoder() {
        EncoderFactory encoderFactory = HLAUtilityFactory.INSTANCE.getEncoderFactory();
        this.coder = encoderFactory.createHLAinteger16BE();
    }

    @Override
    public RepairResponseResultType decode(byte[] buffer) throws DecoderException {
        coder.decode(buffer);
        return RepairResponseResultType.fromValue(coder.getValue());
    }

    @Override
    public byte[] encode(RepairResponseResultType repairResponseResultType) {
        coder.setValue(repairResponseResultType.getValue());
        return coder.toByteArray();
    }

    @Override
    public Class<RepairResponseResultType> getAllowedType() {
        return RepairResponseResultType.class;
    }
}
