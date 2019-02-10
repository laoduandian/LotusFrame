package lotus.net.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import lotus.net.center.myclass.LParticleEffect;
import lotus.net.center.myclass.LScreen;
import lotus.net.center.uieditor.project.widget.TextButtonActor;

public class MenuScreen extends LScreen{
    private static MenuScreen menuScreen;
    public static MenuScreen getInstance() {
        if(menuScreen == null){
            menuScreen = new MenuScreen();
        }
        return menuScreen;
    }
    @Override
    public void show() {
        super.show();
        init();
        if(game.info.is_Add_New)
            add();
    }

    boolean isHead = true;
    private  void init(){
        Image bgImage = new Image(game.texture);
        bgImage.setColor(Color.YELLOW);
        bgImage.setSize(game.info.GAME_WIDTH,game.info.GAME_HEIGHT);
        this.getStage().addActor(bgImage);
        TextButtonActor removeBanner = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        removeBanner.setColor(Color.PINK);
        removeBanner.setPosition(200,300);
        removeBanner.setSize(200,50);
        removeBanner.add(MyGame.getInstance().font.getLabel("移除广告条"));
        this.getStage().addActor(removeBanner);
        removeBanner.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGame.getInstance().app.removeRanners();
            }
        });
        TextButtonActor addBanner = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        addBanner.setColor(Color.PINK);
        addBanner.setPosition(200,400);
        addBanner.setSize(200,50);
        addBanner.add(game.font.getLabel("显示广告条"));
        this.getStage().addActor(addBanner);
        addBanner.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.addBanners(isHead);
                isHead = !isHead;
            }
        });

        TextButtonActor initializeAD = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        initializeAD.setColor(Color.PINK);
        initializeAD.setPosition(200,500);
        initializeAD.setSize(200,50);
        initializeAD.add(game.font.getLabel("加载插屏"));
        this.getStage().addActor(initializeAD);
        initializeAD.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.loadInsertscreen();
            }
        });

        TextButtonActor showInterstitialAd = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        showInterstitialAd.setColor(Color.PINK);
        showInterstitialAd.setPosition(200,600);
        showInterstitialAd.setSize(200,50);
        showInterstitialAd.add(game.font.getLabel("显示插屏"));
        this.getStage().addActor(showInterstitialAd);
        showInterstitialAd.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.showInterstitialAd();
            }
        });


        TextButtonActor showMovie = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        showMovie.setColor(Color.PINK);
        showMovie.setPosition(200,700);
        showMovie.setSize(200,50);
        showMovie.add(game.font.getLabel("显示视频"));
        this.getStage().addActor(showMovie);
        showMovie.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.showMovie(51);
            }
        });

        TextButtonActor moreGame = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        moreGame.setColor(Color.PINK);
        moreGame.setPosition(200,800);
        moreGame.setSize(200,50);
        moreGame.add(game.font.getLabel("更多游戏"));
        this.getStage().addActor(moreGame);
        moreGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.moreGame();
            }
        });
        TextButtonActor pinfen = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        pinfen.setColor(Color.PINK);
        pinfen.setPosition(200,900);
        pinfen.setSize(200,50);
        pinfen.add(game.font.getLabel("评价"));
        this.getStage().addActor(pinfen);
        pinfen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.pinfen();
            }
        });
        TextButtonActor paihang = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        paihang.setColor(Color.PINK);
        paihang.setPosition(200,1000);
        paihang.setSize(200,50);
        paihang.add(game.font.getLabel("排行"));
        this.getStage().addActor(paihang);
        paihang.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.paihang();
                LParticleEffect lParticleEffect = new LParticleEffect(game,L.data.particle.fangkuaiexplode);
                LParticleEffect.ParticleEffectActor actor1= lParticleEffect.obtain();
                actor1.setPosition(200 ,1000);
                MenuScreen.this.getStage().addActor(actor1);


            }
        });

        TextButtonActor gameScreen = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        gameScreen.setColor(Color.PINK);
        gameScreen.setPosition(200,1100);
        gameScreen.setSize(200,50);
        gameScreen.add(game.font.getLabel("游戏界面"));
        this.getStage().addActor(gameScreen);
        gameScreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreenshots(GameScreen.getInstance());
            }
        });


    }

    @Override
    public void resume() {
        game.loadFolder(L.data.class);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void dobackJob() {
        this.game.app.outGame();
    }
}
