namespace TruVideoCoreAndroidBinding;
public class CoreCallback : Java.Lang.Object, TruVideoCoreAndroid.ICoreCallback
{
    private readonly Action<string> _onSuccess;
    private readonly Action<string> _onFailure;

    public CoreCallback(Action<string> onSuccess, Action<string> onFailure) {
        _onSuccess = onSuccess;
        _onFailure = onFailure;
    }

    public void OnSuccess(string result) {
        _onSuccess?.Invoke(result);
    }

    public void OnFailure(string error) {
        _onFailure?.Invoke(error);
    }
}