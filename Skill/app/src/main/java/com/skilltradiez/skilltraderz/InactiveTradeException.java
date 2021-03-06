package com.skilltradiez.skilltraderz;
/*
 *    Team15Alpha
 *    AppName: SkillTradiez (Subject to change)
 *    Copyright (C) 2015  Stephen Andersen, Falon Scheers, Elyse Hill, Noah Weninger, Cole Evans
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * In this application we can have many sorts of exceptions,
 * it is our goal to handle these exceptions in the best way possible. And so we personally
 * went out of our own ways to make a specific exception for inactive trades!
 * This will act as a generalized exception for now and allows us the ability to personalize
 * our particular ways of tackling the problems of an inactive trade! :)
 */

public class InactiveTradeException extends Exception {

    /**CONSTRUCTORS:
     * An empty exception is literally a bottomless hole.
     * Here I make a constructor for basically anything you could ask this exception to do.
     */

    /**
     * Given no parameters, call the superclass of this exception (Exception) with no params.
     */
    InactiveTradeException() {super();}

    /**
     * Given only a string message as a parameter, invoke the superclass with the message String.
     * @param message String input of displayed message.
     */
    public InactiveTradeException(String message) {super(message);}

    /**
     * Given only a throwable of the source of the error, invoke the superclass Exception with it.
     * @param errorSource Throwable Object.
     */
    public InactiveTradeException(Throwable errorSource){ super(errorSource);}

    /**
     * Given both a String message and a Throwable source of error, call the superclass with both.
     * @param message String Object message to display.
     * @param errorSource Throwable Object
     */
    public InactiveTradeException(String message, Throwable errorSource) {
        super(message, errorSource);
    }


}
