package com.summer.kbase.base.net.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;

public class FetchLiveData<T> extends TriggerLiveData<Boolean,T>{

    public Boolean getTriggerValue(){
        return trickLiveData.getValue();
    }


    public void reset(){
        trickLiveData.setValue( null );
    }

    public void trigger(){
        trigger( false );
    }


    @Override
    public void trigger( Boolean isForce ){
        super.trigger( isForce );
    }

    @Override
    public FetchLiveData<T> createSwitchMap( Function<Boolean, LiveData<T>> func ){
        super.createSwitchMap( func );
        return this;
    }

    @Override
    protected boolean isShouldBeSkip(Boolean newForceFetch, Boolean oldForceFetch ){
        return equals( newForceFetch, oldForceFetch ) || ( newForceFetch && !oldForceFetch );
    }

}
