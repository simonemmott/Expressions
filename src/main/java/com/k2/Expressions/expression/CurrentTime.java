package com.k2.Expressions.expression;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * The CurrentTime class holds a date value set when the instance of CurrentValue is created an set to the current date and time
 * This value is then used to derive the return values for the getDate(), getTime() and getTimestamp() methods. This ensures that
 * repeated calls to the same instance of CurrentDate will return consistent values.
 * @author simon
 *
 */
public class CurrentTime {
	
	private java.util.Date utilDate;
	
	/**
	 * Create and instance of CurrentDate setting the date to the current date
	 */
	public CurrentTime() {
		utilDate = Calendar.getInstance().getTime();
	}
	
	/**
	 * Reset the date of this CurrentDate to the current date
	 * @return	This CurrentDate for method chaining
	 */
	public CurrentTime reset() {
		utilDate = Calendar.getInstance().getTime();
		return this;
	}
	
	/**
	 * Get the date value of this CurrentDate instance.
	 * @return	The date value of this current date
	 */
	public Date getDate() { return new Date(utilDate.getTime()); }
	
	/**
	 * Get the time value of this CurrentDate instance
	 * @return	The time value of this current date
	 */
	public Time getTime() { return new Time(utilDate.getTime()); }
	
	/**
	 * Get the timestamp of this CurrentDate instance
	 * @return	The timestamp value of this current date
	 */
	public Timestamp getTimestamp() { return new Timestamp(utilDate.getTime()); }

}
