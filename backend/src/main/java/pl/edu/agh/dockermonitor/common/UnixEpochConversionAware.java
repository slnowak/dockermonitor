package pl.edu.agh.dockermonitor.common;

/**
 * Created by novy on 09.06.15.
 */
public interface UnixEpochConversionAware {

    default long fromUnixEpoch(long unixEpochTime) {
        return unixEpochTime * 1000;
    }
}
