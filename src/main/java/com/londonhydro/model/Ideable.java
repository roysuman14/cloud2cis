/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  Ideable.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Juan Pablo Francisconi, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  10Jan13     Juan Pablo Francisconi 	Original version
 */

package com.londonhydro.model;

/**
 * Ideable - ID - Interface
 * 
 * @author Juan Pablo Francisconi (jpfrancisconi@affsys.com)
 */
public interface Ideable<ID_TYPE>
{

    ID_TYPE getId();

}
