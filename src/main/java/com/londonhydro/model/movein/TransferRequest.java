/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *	File Name:	MoveOutRequest.java
 *
 *	Facility:	London Hydro Web App
 *
 *	Purpose:	This module contains a class implementation
 *
 *	Author:		Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  	Date			Author			Description
 *	----------------	---------------------	--------------------
 *	07May13		        Love Talwar		Original version
 *
 */
package com.londonhydro.model.movein;

import java.io.Serializable;

import com.londonhydro.model.moveout.MoveOutRequest;

public class TransferRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private MoveInRequest movein;
	private MoveOutRequest moveout;

	public MoveInRequest getMovein() {
		return movein;
	}

	public void setMovein(MoveInRequest movein) {
		this.movein = movein;
	}

	public MoveOutRequest getMoveout() {
		return moveout;
	}

	public void setMoveout(MoveOutRequest moveout) {
		this.moveout = moveout;
	}

}
