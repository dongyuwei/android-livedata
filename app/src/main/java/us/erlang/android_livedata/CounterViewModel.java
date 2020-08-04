package us.erlang.android_livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class CounterViewModel extends ViewModel {
    private MutableLiveData<Long> counter;
    private Disposable disposable;

    public MutableLiveData<Long> getCounter() {
        if (counter == null) {
            counter = new MutableLiveData<Long>();
        }
        return counter;
    }

    public void startCounter() {
        disposable = (Disposable) Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long counter) throws Exception {
                        getCounter().postValue(counter);
                    }
                });
    }

    @Override
    protected void onCleared() {
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
        super.onCleared();
    }
}
