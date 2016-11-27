package com.timingapi.base;

/**
 * Created by artyom on 22-Nov-16.
 */
public class PageLoadTiming {
    public Long navigationStart;
    public Long unloadEventStart;
    public Long unloadEventEnd;
    public Long redirectStart;
    public Long redirectEnd;
    public Long fetchStart;
    public Long domainLookupStart;
    public Long domainLookupEnd;
    public Long connectStart;
    public Long connectEnd;
    public Long secureConnectionStart;
    public Long requestStart;
    public Long responseStart;
    public Long responseEnd;
    public Long domLoading;
    public Long domInteractive;
    public Long domContentLoadedEventStart;
    public Long domContentLoadedEventEnd;
    public Long domComplete;
    public Long loadEventStart;
    public Long loadEventEnd;
    public Long getDomainLookupTime() {
        return domainLookupEnd-domainLookupStart;
    }
    public Long getRedirectionTime() {
        return fetchStart-navigationStart;
    }
    public Long getServerConnectionTime() {
        return connectEnd-connectStart;
    }
    public Long getServerResponseTime() {
        return responseEnd-requestStart;
    }
    public Long getPageDownloadTime() {
        return responseEnd-responseStart;
    }
    public Long getDOMInteractiveTime() {
        return domInteractive-navigationStart;
    }
    public Long getDOMContentLoadedTime() {
        return domContentLoadedEventStart-navigationStart;
    }
    public Long getPageLoadTime() {
        return loadEventStart-navigationStart;
    }
    public Long getFrontEndTime() {
        return loadEventStart-responseEnd;
    }
    public Long getBackEndTime() {
        return responseStart-navigationStart;
    }

}
