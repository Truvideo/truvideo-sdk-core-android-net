using Android.Runtime;
using Java.Interop;

namespace TruVideoCoreAndroidBinding;

[Register("com.truvideo.core.CoreCallback")]
public interface ICoreCallback : IJavaObject, IDisposable
{
    [Export("onSuccess")]
    void OnSuccess(string result);

    [Export("onFailure")]
    void OnFailure(string error);
}