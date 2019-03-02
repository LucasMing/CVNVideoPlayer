#### **目录介绍**
- **1.关于此视频封装库介绍**
- 1.1 能够满足那些业务需求
- 1.2 对比同类型的库有哪些优势
- **2.关于使用方法说明**
- 2.1 关于gradle引用说明
- 2.2 添加布局
- 2.3 最简单的视频播放器参数设定
- 2.4 注意的问题
- 2.5 关于开源库中的类说明
- 2.6 暴露接口让用户实现返回键和登录和购买会员点击事件逻辑
- **3.关于播放类型说明**
- 3.1 普通视频播放
- 3.2 list页面视频播放
- 3.3 小窗口视频播放
- 3.4 类似爱奇艺，优酷会员试看视频播放
- 3.5 关于封装库中日志打印
- **4.关于相关方法说明**
- 4.1 关于VideoPlayer类[播放器]中方法说明
- 4.2 关于VideoPlayerController类[控制器]中方法说明
- 4.3 关于对象的销毁
- **5.关于封装的思路**
- 5.1 参考的案例思路
- 5.2 封装的基本思路
- 5.3 关于窗口切换分析
- 5.4 关于VideoPlayerManager视频播放器管理器分析
- 5.5 关于VideoPlayerController视频控制器分析
- 5.6 关于InterVideoPlayer接口分析
- **6.关于如何自定义你想要的视频播放模式**
- 6.1 自定义视频播放器
- **7.关于遇到的问题说明**
- 7.1 视频难点
- 7.2 遇到的bug
- 7.3 后期需要实现的功能


### 1.关于此视频封装库介绍
#### 1.1 能够满足那些业务需求

> **A基础功能**
> 
- 1.1.1 能够自定义视频加载loading类型，设置视频标题，设置视频底部图片，设置播放时长等基础功能
- 1.1.2 可以切换播放器的视频播放状态，播放错误，播放未开始，播放开始，播放准备中，正在播放，暂停播放，正在缓冲等等状态
- 1.1.3 可以自由设置播放器的播放模式，比如，正常播放，全屏播放，和小屏幕播放。其中全屏播放支持旋转屏幕。
- 1.1.4 可以支持多种视频播放类型，比如，原生封装视频播放器，还有基于ijkplayer封装的播放器。
- 1.1.5 可以设置是否隐藏播放音量，播放进度，播放亮度等，可以通过拖动seekBar改变视频进度。还支持设置n秒后不操作则隐藏头部和顶部布局功能


-


> **B高级功能**
> 
- 1.1.6 支持一遍播放一遍缓冲的功能，其中缓冲包括两部分，第一种是播放过程中缓冲，第二种是暂停过程中缓冲
- 1.1.7 基于ijkplayer的封装播放器，支持多种格式视频播放
- 1.1.8 可以设置是否记录播放位置，设置播放速度，设置屏幕比例
- 1.1.9 支持滑动改变音量【屏幕右边】，改变屏幕亮度【屏幕左边】，支持切换视频清晰度模式
- 1.1.0 支持list页面中视频播放，滚动后暂停播放，播放可以自由设置是否记录状态。并且还支持删除视频播放位置状态。


-


> **C拓展功能**
> 
- **C1产品需求：类似优酷，爱奇艺视频播放器部分逻辑。比如如果用户没有登录也没有看视频权限，则提示试看视频[自定义布局]；如果用户没有登录但是有看视频权限，则正常观看；如果用户登录，但是没有充值会员，部分需要权限视频则进入试看模式，试看结束后弹出充值会员界面；如果用户余额不足，比如余额只有99元，但是视频观看要199元，则又有其他提示。**
- C2自身需求：比如封装好了视频播放库，那么点击视频上登录按钮则跳到登录页面；点击充值会员页面也跳到充值页面。这个通过定义接口，可以让使用者通过方法调用，灵活处理点击事件。
- C.1.1 实现了上面两个需求，灵活可拓展性强。
- C.1.2 对于设置视频的宽高，建议设置成4：3或者16：9或者常用比例，如果不是常用比例，则可能会有黑边。其中黑边的背景可以设置
- C.1.3 可以设置播放有权限的视频时的各种文字描述，而没有把它写在封装库中，使用者自己设定
- C.1.4 锁定屏幕功能


-


> **D待添加功能**
> 
- D.1.1 可以支持屏幕截图功能，视频添加水印效果
- D.1.2 支持弹幕功能
- D.1.3 后期待定


### 2.关于使用方法说明
#### 2.1 关于gradle引用说明
- **2.1.1直接引用这段代码就可以**
```
compile 'cn.yc:YCVideoPlayerLib:2.2' 
```

#### 2.2 添加布局
- 注意，在实际开发中，由于Android手机碎片化比较严重，分辨率太多了，建议灵活设置布局的宽高比为4：3或者16：9或者你认为合适的，可以用代码设置。
- 如果宽高比变形，则会有黑边

```
<org.yczbj.ycvideoplayerlib.VideoPlayer
	android:id="@+id/video_player"
	android:layout_width="match_parent"
	android:layout_height="240dp"/>
```


#### 2.3 最简单的视频播放器参数设定
- **2.3.1 这个是最简单视频播放器的设置参数代码**
```
	//设置播放类型
	// IjkPlayer or MediaPlayer
	videoPlayer1.setPlayerType(VideoPlayer.TYPE_NATIVE);
	//网络视频地址
	String videoUrl = DataUtil.getVideoListData().get(0).getVideoUrl();
	//设置视频地址和请求头部
	videoPlayer1.setUp(videoUrl, null);
	//是否从上一次的位置继续播放
	videoPlayer1.continueFromLastPosition(true);
	//设置播放速度
	videoPlayer1.setSpeed(1.0f);
	//创建视频控制器
	VideoPlayerController controller = new VideoPlayerController(this);
	controller.setTitle("办快来围观拉，自定义视频播放器可以播放视频拉");
	//设置视频时长
	controller.setLength(98000);
	//设置5秒不操作后则隐藏头部和底部布局视图
	controller.setHideTime(5000);
	//controller.setImage(R.drawable.image_default);
	ImageUtil.loadImgByPicasso(this, R.drawable.image_default, R.drawable.image_default, controller.imageView());
	//设置视频控制器
	videoPlayer1.setController(controller);
```

- **2.3.2 关于模仿爱奇艺登录会员权限功能代码**
```
	//设置视频加载缓冲时加载窗的类型，多种类型
	controller.setLoadingType(2);
	ArrayList<String> content = new ArrayList<>();
	content.add("试看结束，yc观看全部内容请开通会员1111。");
	content.add("试看结束，yc观看全部内容请开通会员2222。");
	content.add("试看结束，yc观看全部内容请开通会员3333。");
	content.add("试看结束，yc观看全部内容请开通会员4444。");
	controller.setMemberContent(content);
	controller.setHideTime(5000);
	//设置设置会员权限类型，第一个参数是否登录，第二个参数是否有权限看，第三个参数试看完后展示的文字内容，第四个参数是否保存进度位置
	controller.setMemberType(false,false,3,true);
	controller.imageView().setBackgroundResource(R.color.blackText);
	//ImageUtil.loadImgByPicasso(this, R.color.blackText, R.drawable.image_default, controller.imageView());
	//设置试看结束后，登录或者充值会员按钮的点击事件
	controller.setOnMemberClickListener(new OnMemberClickListener() {
		@Override
		public void onClick(int type) {
			switch (type){
				case ConstantKeys.Gender.LOGIN:
					//调到用户登录也米娜
					startActivity(MeLoginActivity.class);
					break;
				case ConstantKeys.Gender.MEMBER:
					//调到用户充值会员页面
					startActivity(MeMemberActivity.class);
					break;
				default:
					break;
			}
		}
	});
```

- **2.3.3其他设置，让体验更好**
- **如果是在Activity中的话，建议设置下面这段代码**
```
    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) return;
        super.onBackPressed();
    }
```
- **如果是在Fragment中的话，建议设置下面这段代码**
```
    //在宿主Activity中设置代码如下
    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) return;
        super.onBackPressed();
    }

    //--------------------------------------------------

    //在此Fragment中设置代码如下
    @Override
    public void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }
```

#### 2.4 注意的问题
- **2.4.1如果是全屏播放，则需要在清单文件中设置当前activity的属性值**
- android:configChanges 保证了在全屏的时候横竖屏切换不会执行Activity的相关生命周期，打断视频的播放
- android:screenOrientation 固定了屏幕的初始方向
- 这两个变量控制全屏后和退出全屏的屏幕方向

```
	<activity android:name=".ui.test2.TestMyActivity"
		android:configChanges="orientation|keyboardHidden|screenSize"
		android:screenOrientation="portrait"/>
```


#### 2.6 暴露接口让用户实现返回键和登录和购买会员点击事件逻辑
```
	controller.setOnMemberClickListener(new OnMemberClickListener() {
		@Override
		public void onClick(int type) {
			switch (type){
				case ConstantKeys.Gender.LOGIN:
					//调到用户登录也米娜
					startActivity(MeLoginActivity.class);
					break;
				case ConstantKeys.Gender.MEMBER:
					//调到用户充值会员页面
					startActivity(MeMemberActivity.class);
					break;
				default:
					break;
			}
		}
	});
	controller.setOnVideoBackListener(new OnVideoBackListener() {
		@Override
		public void onBackClick() {
			onBackPressed();
		}
	});
```



### 3.关于播放类型说明
#### 3.1 普通视频播放
- 3.1.1 这一步操作可以直接看第二部分内容——关于使用方法说明

#### 3.2 list页面视频播放
- **3.2.1如何在list页面设置视频**
- 第一步：在activity或者fragment中

```
	recyclerView.setLayoutManager(new LinearLayoutManager(this));
	recyclerView.setHasFixedSize(true);
	VideoAdapter adapter = new VideoAdapter(this, DataUtil.getVideoListData());
	recyclerView.setAdapter(adapter);
	//注意：下面这个方法不能漏掉
	recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
		@Override
		public void onViewRecycled(RecyclerView.ViewHolder holder) {
			VideoPlayer videoPlayer = ((VideoAdapter.VideoViewHolder) holder).mVideoPlayer;
			if (videoPlayer == VideoPlayerManager.instance().getCurrentVideoPlayer()) {
				VideoPlayerManager.instance().releaseVideoPlayer();
			}
		}
	});
```


- 第二步：在RecyclerView的适配器Adapter中

```
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<Video> mVideoList;

    VideoAdapter(Context context, List<Video> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_test_my_video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(itemView);
        //创建视频播放控制器，主要只要创建一次就可以呢
        VideoPlayerController controller = new VideoPlayerController(mContext);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = mVideoList.get(position);
        holder.bindData(video);
    }

    @Override
    public int getItemCount() {
        return mVideoList==null ? 0 : mVideoList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoPlayerController mController;
        VideoPlayer mVideoPlayer;

        VideoViewHolder(View itemView) {
            super(itemView);
            mVideoPlayer = (VideoPlayer) itemView.findViewById(R.id.nice_video_player);
            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
            // 宽度为屏幕宽度
            params.width = itemView.getResources().getDisplayMetrics().widthPixels;
            // 高度为宽度的9/16
            params.height = (int) (params.width * 9f / 16f);
            mVideoPlayer.setLayoutParams(params);
        }

        /**
         * 设置视频控制器参数
         * @param controller            控制器对象
         */
        void setController(VideoPlayerController controller) {
            mController = controller;
            mVideoPlayer.setController(mController);
        }

        void bindData(Video video) {
            mController.setTitle(video.getTitle());
            mController.setLength(video.getLength());
            Glide.with(itemView.getContext())
                    .load(video.getImageUrl())
                    .placeholder(R.drawable.image_default)
                    .crossFade()
                    .into(mController.imageView());
            mVideoPlayer.setUp(video.getVideoUrl(), null);
        }
    }
}
```

### 4.关于相关方法说明
#### 4.1 关于VideoPlayer类中方法说明
- **4.1.1 关于一定需要这四步**

```
	//设置播放类型
	// IjkPlayer or MediaPlayer
	videoPlayer1.setPlayerType(VideoPlayer.TYPE_NATIVE);
	//设置视频地址和请求头部
	videoPlayer1.setUp(videoUrl, null);
	//创建视频控制器
	VideoPlayerController controller = new VideoPlayerController(this);
	//设置视频控制器
	videoPlayer1.setController(controller);
```

- **4.1.2 关于VideoPlayer中设置属性方法**
```
	//设置播放类型
	// MediaPlayer
	videoPlayer.setPlayerType(VideoPlayer.TYPE_NATIVE);
	// IjkPlayer
	videoPlayer.setPlayerType(VideoPlayer.TYPE_IJK);
	//网络视频地址
	String videoUrl = DataUtil.getVideoListData().get(1).getVideoUrl();
	//设置视频地址和请求头部
	videoPlayer.setUp(videoUrl, null);
	//是否从上一次的位置继续播放
	videoPlayer.continueFromLastPosition(false);
	//设置播放速度
	videoPlayer.setSpeed(1.0f);
	//设置播放位置
	//videoPlayer.seekTo(3000);
	//设置音量
	videoPlayer.setVolume(50);

	//设置全屏播放
	videoPlayer.enterFullScreen();
	//设置小屏幕播放
	videoPlayer.enterTinyWindow();
	//退出全屏
	videoPlayer.exitFullScreen();
	//退出小窗口播放
	videoPlayer.exitTinyWindow();
	//释放，内部的播放器被释放掉，同时如果在全屏、小窗口模式下都会退出
	videoPlayer.release();
	//释放播放器，注意一定要判断对象是否为空，增强严谨性
	videoPlayer.releasePlayer();
```

- **4.1.3 关于VideoPlayer中获取属性方法**
```
        //是否从上一次的位置继续播放，不必须
        videoPlayer.continueFromLastPosition(false);
        //获取最大音量
        int maxVolume = videoPlayer.getMaxVolume();
        //获取音量值
        int volume = videoPlayer.getVolume();
        //获取持续时长
        long duration = videoPlayer.getDuration();
        //获取播放位置
        long currentPosition = videoPlayer.getCurrentPosition();
        //获取缓冲区百分比
        int bufferPercentage = videoPlayer.getBufferPercentage();
        //获取播放速度
        float speed = videoPlayer.getSpeed(1);
```

- **4.1.4 关于VideoPlayer中设置播放状态方法**
```
	//开始播放
	videoPlayer.start();
	//开始播放，从某位置播放
	videoPlayer.start(3000);
	//重新播放
	videoPlayer.restart();
	//暂停播放
	videoPlayer.pause();
```

#### 4.2 关于VideoPlayerController类[控制器]中方法说明
- **4.2.1 关于控制器方法**

```
	//创建视频控制器
	VideoPlayerController controller = new VideoPlayerController(this);
	//设置视频标题
	controller.setTitle("高仿优酷视频播放页面");
	//设置视频时长
	//controller.setLength(98000);
	//设置视频加载缓冲时加载窗的类型，多种类型
	controller.setLoadingType(2);
	ArrayList<String> content = new ArrayList<>();
	content.add("试看结束，观看全部内容请开通会员1111。");
	content.add("试看结束，观看全部内容请开通会员2222。");
	content.add("试看结束，观看全部内容请开通会员3333。");
	content.add("试看结束，观看全部内容请开通会员4444。");
	//设置会员权限话术内容
	controller.setMemberContent(content);
	//设置不操作后，5秒自动隐藏头部和底部布局
	controller.setHideTime(5000);
	//设置设置会员权限类型，第一个参数是否登录，第二个参数是否有权限看，第三个参数试看完后展示的文字内容，第四个参数是否保存进度位置
	controller.setMemberType(false,false,3,true);
	//设置背景图片
	controller.imageView().setBackgroundResource(R.color.blackText);
	//ImageUtil.loadImgByPicasso(this, R.color.blackText, R.drawable.image_default, controller.imageView());
	//设置试看结束后，登录或者充值会员按钮的点击事件
	controller.setOnMemberClickListener(new OnMemberClickListener() {
		@Override
		public void onClick(int type) {
			switch (type){
				case ConstantKeys.Gender.LOGIN:
					//调到用户登录也米娜
					startActivity(MeLoginActivity.class);
					break;
				case ConstantKeys.Gender.MEMBER:
					//调到用户充值会员页面
					startActivity(MeMemberActivity.class);
					break;
				default:
					break;
			}
		}
	});
	//设置视频清晰度
	//videoPlayer.setClarity(list,720);
	//设置视频控制器
	videoPlayer.setController(controller);
```

#### 4.3 关于对象的销毁
- 4.3.1在VideoPlayer中如何释放资源的呢？源代码如下所示

```
    @Override
    public void release() {
        // 保存播放位置
        if (isPlaying() || isBufferingPlaying() || isBufferingPaused() || isPaused()) {
            VideoPlayerUtils.savePlayPosition(mContext, mUrl, getCurrentPosition());
        } else if (isCompleted()) {
            //如果播放完成，则保存播放位置为0，也就是初始位置
            VideoPlayerUtils.savePlayPosition(mContext, mUrl, 0);
        }
        // 退出全屏或小窗口
        if (isFullScreen()) {
            exitFullScreen();
        }
        if (isTinyWindow()) {
            exitTinyWindow();
        }
        mCurrentMode = MODE_NORMAL;

        // 释放播放器
        releasePlayer();

        // 恢复控制器
        if (mController != null) {
            mController.reset();
        }
        // gc回收
        Runtime.getRuntime().gc();
    }
    //释放播放器，注意一定要判断对象是否为空，增强严谨性
    @Override
    public void releasePlayer() {
        if (mAudioManager != null) {
            //放弃音频焦点。使以前的焦点所有者(如果有的话)接收焦点。
            mAudioManager.abandonAudioFocus(null);
            //置空
            mAudioManager = null;
        }
        if (mMediaPlayer != null) {
            //释放视频焦点
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        //从视图中移除TextureView
        mContainer.removeView(mTextureView);
        if (mSurface != null) {
            mSurface.release();
            mSurface = null;
        }
        //如果SurfaceTexture不为null，则释放
        if (mSurfaceTexture != null) {
            mSurfaceTexture.release();
            mSurfaceTexture = null;
        }
        //设置状态
        mCurrentState = STATE_IDLE;
    }
```


```
  Picasso.with(this)
                .load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
                .into(jzVideo.thumbImageView);
```



#### 5.3 关于窗口切换分析
- **5.3.1 关于窗口切换调用的代码**

```
	//设置全屏播放
	videoPlayer.enterFullScreen();
	//设置小屏幕播放
	videoPlayer.enterTinyWindow();
	//退出全屏
	videoPlayer.exitFullScreen();
	//退出小窗口播放
	videoPlayer.exitTinyWindow();
	//释放，内部的播放器被释放掉，同时如果在全屏、小窗口模式下都会退出
	videoPlayer.release();
	//释放播放器，注意一定要判断对象是否为空，增强严谨性
	videoPlayer.releasePlayer();
```



#### 5.5 关于VideoPlayerController视频控制器分析
- **5.5.1VideoPlayerController的作用**
- 播放控制界面上，播放、暂停、播放进度、缓冲动画、全屏/小屏等触发都是直接调用播放器对应的操作的。
- **5.5.2VideoPlayerController的方法如下所示**

```
	//创建视频控制器
	VideoPlayerController controller = new VideoPlayerController(this);
	//设置视频标题
	controller.setTitle("高仿优酷视频播放页面");
	//设置视频时长
	//controller.setLength(98000);
	//设置视频加载缓冲时加载窗的类型，多种类型
	controller.setLoadingType(2);
	ArrayList<String> content = new ArrayList<>();
	content.add("试看结束，观看全部内容请开通会员1111。");
	content.add("试看结束，观看全部内容请开通会员2222。");
	content.add("试看结束，观看全部内容请开通会员3333。");
	content.add("试看结束，观看全部内容请开通会员4444。");
	//设置会员权限话术内容
	controller.setMemberContent(content);
	//设置不操作后，5秒自动隐藏头部和底部布局
	controller.setHideTime(5000);
	//设置设置会员权限类型，第一个参数是否登录，第二个参数是否有权限看，第三个参数试看完后展示的文字内容，第四个参数是否保存进度位置
	controller.setMemberType(false,false,3,true);
	//设置背景图片
	controller.imageView().setBackgroundResource(R.color.blackText);
	//ImageUtil.loadImgByPicasso(this, R.color.blackText, R.drawable.image_default, controller.imageView());
	//设置试看结束后，登录或者充值会员按钮的点击事件
	controller.setOnMemberClickListener(new OnMemberClickListener() {
		@Override
		public void onClick(int type) {
			switch (type){
				case ConstantKeys.Gender.LOGIN:
					//调到用户登录也米娜
					startActivity(MeLoginActivity.class);
					break;
				case ConstantKeys.Gender.MEMBER:
					//调到用户充值会员页面
					startActivity(MeMemberActivity.class);
					break;
				default:
					break;
			}
		}
	});
	//设置视频清晰度
	//videoPlayer.setClarity(list,720);
	//设置视频控制器
	videoPlayer.setController(controller);
```


### 6.关于如何自定义你想要的视频播放模式
#### 6.1 自定义视频播放器
- **6.1.1如何自定义自己的播放器**
- 第一步：首先继承VideoPlayer这个类
- 第二步：然后重写部分你需要更改功能的方法，只需要选择你需要重写的方法即可。
- **6.1.2代码展示如下所示**
```
	public class YCVideoPlayer extends VideoPlayer {

		public YCVideoPlayer(Context context) {
			super(context);
		}

		@Override
		public void setUp(String url, Map<String, String> headers) {
			super.setUp(url, headers);
		}

		@Override
		public void setController(AbsVideoPlayerController controller) {
			super.setController(controller);
		}

		@Override
		public void setPlayerType(int playerType) {
			super.setPlayerType(playerType);
		}

		@Override
		public void continueFromLastPosition(boolean continueFromLastPosition) {
			super.continueFromLastPosition(continueFromLastPosition);
		}

		@Override
		public void setSpeed(float speed) {
			super.setSpeed(speed);
		}

		@Override
		public void start() {
			super.start();
		}

		@Override
		public void start(long position) {
			super.start(position);
		}

		@Override
		public void restart() {
			super.restart();
		}

		@Override
		public void pause() {
			super.pause();
		}

		@Override
		public void seekTo(long pos) {
			super.seekTo(pos);
		}

		@Override
		public void setVolume(int volume) {
			super.setVolume(volume);
		}

		@Override
		public boolean isIdle() {
			return super.isIdle();
		}

		@Override
		public boolean isPreparing() {
			return super.isPreparing();
		}

		@Override
		public boolean isPrepared() {
			return super.isPrepared();
		}

		@Override
		public boolean isBufferingPlaying() {
			return super.isBufferingPlaying();
		}

		@Override
		public boolean isBufferingPaused() {
			return super.isBufferingPaused();
		}

		@Override
		public boolean isPlaying() {
			return super.isPlaying();
		}

		@Override
		public boolean isPaused() {
			return super.isPaused();
		}

		@Override
		public boolean isError() {
			return super.isError();
		}
	}
```



### 7.关于遇到的问题说明
#### 7.1 视频难点
- 7.1.1 当视频切换全屏或者从全屏切换到正常小屏幕时，如何管理activity的生命周期
- 7.1.2 在列表list页面，滑动显示小窗口，那么什么时候显示小窗口呢？关于RecyclerView的滑动位移超出屏幕有没有更好的解决办法？
- 7.1.2 当屏幕从全屏退出时，播放位置要滑到记录的位置，代码逻辑复杂，如何避免耦合度太高

#### 7.2 遇到的bug
- 7.2.1 当视频切花时，如何避免视频不卡顿
- 7.2.2 在fragment中，当左右滑动出另一个fragment中，视频还在播放，怎么样处理这部分逻辑
- 7.2.3 在显示缓冲比时，网络不好或者暂停缓冲时有问题，所以暂停还没有添加该功能
- 7.2.4 播放进度条seekbar跳动问题
- 7.2.5 部分华为手机播放视频有问题，在找原因
- 7.2.6 在拖动时显示当前帧的画面图片，类似优酷那个功能，最终还是没有实现

#### 7.3 后期需要实现的功能
- 7.3.1 如果有多集视频，则添加上一集和下一集的功能
- 7.3.2 拖动滑动条，显示帧画面
- 7.3.3 实现弹幕功能
- 7.4.4 有些手机播放有问题，测试找问题
- 7.5.5 切换视频清晰度有问题，是重新开始播放，因为切换清晰度时，调用的视频链接是不同的。比如高清视频和标准视频链接是不同的，所以难以实现切换后记录位置播放。但是看了下优酷，爱奇艺视频，切换后是接着之前观看的位置播放，这个需要思考下怎么实现。欢迎同行给出好的建议。
- 7.5.6 待定









