/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;
import it.sauronsoftware.cron4j.Scheduler;
/**
 *
 * @author udit
 */
public class FeedSchedular {
    Scheduler s = null;
    RssCrawling rc = null;
    String feedUrl;
    String pattern;
    long minutes;

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }
    boolean isDone = false;
    boolean isScheduled = false;

    public boolean isIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(boolean isScheduled) {
        this.isScheduled = isScheduled;
    }

    public FeedSchedular(String feedUrl, String pattern, long minutes) {
        this.feedUrl = feedUrl;
        this.pattern = pattern;
        this.minutes = minutes;
    }

    public void startSchedule() {
        isScheduled = true;
        rc = new RssCrawling();
        s = new Scheduler();
        // Schedule a once-a-minute task.
        //"* * * * *"
        //"*/1 * * * *"
        s.schedule(pattern, new Runnable() {
            public void run() {
                rc.crawlArticles(feedUrl);
                System.out.println("-----------------------------------");
            }
        });
        // Starts the scheduler.
        s.start();

        // Will run for ten minutes.
        try {
            Thread.sleep(1000L * 60L * minutes);
        } catch (InterruptedException e) {
            ;
        }

        s.stop();
        isDone = true;
        isScheduled = false;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.feedUrl != null ? this.feedUrl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FeedSchedular other = (FeedSchedular) obj;

        if ((this.feedUrl == null) ? (other.feedUrl != null) : !this.feedUrl.equals(other.feedUrl)) {
            return false;
        }
        if (this.isDone != other.isDone) {
            return false;
        }
        if (this.isScheduled != other.isScheduled) {
            return false;
        }
        return true;
    }
}
