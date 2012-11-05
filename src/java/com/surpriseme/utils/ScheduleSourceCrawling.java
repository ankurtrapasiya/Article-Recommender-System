/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

/**
 *
 * @author udit
 */
public class ScheduleSourceCrawling implements Runnable{
   private FeedSchedular scheduler;

    public ScheduleSourceCrawling(FeedSchedular scheduler) {
        this.scheduler = scheduler;
    }

    public FeedSchedular getScheduler() {
        return scheduler;
    }

    public void setScheduler(FeedSchedular scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        if (scheduler != null) {

            scheduler.startSchedule();
        }
    }

    @Override
    public int hashCode() {
        return this.scheduler.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScheduleSourceCrawling) {
            ScheduleSourceCrawling ssc = (ScheduleSourceCrawling) obj;

            return this.scheduler.equals(ssc.getScheduler());
        }
        return false;
    }  
}
