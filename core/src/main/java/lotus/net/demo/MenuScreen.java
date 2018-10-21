package lotus.net.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LScreen;
import lotus.net.center.uieditor.project.widget.ButtonActor;
import lotus.net.center.uieditor.project.widget.TextButtonActor;

public class MenuScreen extends LScreen{
    public MenuScreen(LGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        init();
        if(game.info.is_Add_New)
            add();
    }
    private  void init(){
        TextButtonActor addBanner = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        addBanner.setColor(Color.PINK);
        addBanner.setPosition(200,400);
        addBanner.setSize(200,50);
        addBanner.add(game.font.getLabel("显示广告条"));
        this.getStage().addActor(addBanner);
        addBanner.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.this.game.app.addBanners();
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
                MenuScreen.this.game.app.showMovie(0);
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
                game.setScreenshots(game.getScreen(GameScreen.class));
            }
        });


    }


    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void dobackJob() {

    }
}
