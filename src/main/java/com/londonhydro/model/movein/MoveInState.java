/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *	File Name:	MoveOutState.java
 *
 *	Facility:	London Hydro Web App
 *
 *	Purpose:	This module contains a class implementation
 *
 *	Author:		vpinchevski, Affinity Systems
 *
 *  Revision History
 *
 *  	Date			Author			Description
 *	----------------	---------------------	--------------------
 *	Apr 22, 2013		vpinchevski		Original version
 *
 */
package com.londonhydro.model.movein;

/**
 * @author vpinchevski
 *
 */
public enum MoveInState {
	None, beginMove, confirmInfo, provideServiceAddressPhone, provideOwnerDetails, setMoveInDate, pendingCreditCheck, completeMove, cancelMove
}
