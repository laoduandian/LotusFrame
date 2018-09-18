package lotus.net.center.uieditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import lotus.net.center.freefont.VLabel;
import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.MyGroup;
import lotus.net.center.myclass.data.UIString;
import lotus.net.center.uieditor.project.model.ObjectData;
import lotus.net.center.uieditor.project.widget.AnchorType;
import lotus.net.center.uieditor.project.widget.ButtonActor;
import lotus.net.center.uieditor.project.widget.ButtonGroup;
import lotus.net.center.uieditor.project.widget.LImageNumber;


public class EditorInto {
	private ObjectData gameValue;
	private LGame game;
	public EditorInto(LGame game) {
		this.game = game;
	}
	public void read(String name,Group group,TextureAtlas atlas){
		gameValue = game.json.fromJson(ObjectData.class, Gdx.files.internal(name));
		refreshActors(group,atlas);
	}
	
	private void refreshActors(Group group,TextureAtlas atlas) {
		setBasicInformation(group, gameValue);
		group.setName(gameValue.getName());
		for (ObjectData data : gameValue.getChilds()){
			creatBaBy(data,group,atlas);
		}
	}
	private void creatBaBy(ObjectData data,Group group,TextureAtlas atlas){
		Actor actor = null;
		Group childGroup = null;
		switch (data.getWidgetType()) {
		case W_SPRITE:
			if(data.getVisible()!=null && !data.getVisible())
				break;
			TextureRegion region = atlas.findRegion(data.getFileData().getFileName());
			if(data.isFlipX()||data.isFlipY()){
				region = new TextureRegion(atlas.findRegion(data.getFileData().getFileName()));
				region.flip(data.isFlipX(), data.isFlipY());
			}
			if(region == null)
				return;
			if(data.getTouchable()!=null){
				actor = new ButtonActor(region);
			}else{
				actor = new Image(region);
				actor.setTouchable(Touchable.disabled);
			}
			break;
		case W_BUTTON:
			ButtonStyle style = new ButtonStyle();
			style.up = new TextureRegionDrawable(atlas.findRegion(data.getUp().getFileName()));
			if(data.getDown()!=null)
				style.down = new TextureRegionDrawable(atlas.findRegion(data.getDown().getFileName()));
			if(data.getOver()!=null)
				style.over = new TextureRegionDrawable(atlas.findRegion(data.getOver().getFileName()));
			if(data.getChecked()!=null)
				style.checked = new TextureRegionDrawable(atlas.findRegion(data.getChecked().getFileName()));
			if(data.getCheckedOver()!=null)
				style.checkedOver = new TextureRegionDrawable(atlas.findRegion(data.getCheckedOver().getFileName()));
			if(data.getDisabled()!=null)
				style.disabled = new TextureRegionDrawable(atlas.findRegion(data.getDisabled().getFileName()));
			actor = new Button(style);
			break;
		case W_IMAGENUMBER:
			LImageNumber imageNumber = new LImageNumber();
			imageNumber.setRegion(atlas.findRegion(data.getFileData().getFileName()));
			imageNumber.setImageNumberContent(data.getImageNumberContent());
			imageNumber.setRegions();
			imageNumber.setText("00");
			imageNumber.setAnchorX(data.getAnchor().getX());
			imageNumber.setAnchorY(data.getAnchor().getY());
			actor = imageNumber;
			break;
		case W_GROUP:
			if(data.getTouchable()!=null){
				childGroup = new ButtonGroup(this.game);
				childGroup.setSize(data.getSize().getWidth(), data.getSize().getHeight());
				actor = childGroup;
			}else {
				childGroup = new MyGroup(this.game);
				childGroup.setSize(data.getSize().getWidth(), data.getSize().getHeight());
				actor = childGroup;
			}
			break;
		case W_LABEL:
			String contentText ="";
			if(data.getLabelContent() == null)
				data.setLabelContent("Text");
			try {
				if(UIString.myBundle.get(data.getLabelContent())!=null) {
					contentText = UIString.myBundle.get(data.getLabelContent());
				}
			} catch (Exception e) {
				contentText = "Text";
			}
			VLabel label = game.font.getLabel(contentText, data.getFontSize());
			label.setSize(data.getSize().getWidth(), data.getSize().getHeight());
			setAnchorType(label,data.getAnchorType());
			label.setTouchable(Touchable.disabled);
			if(data.getColor()!=null)
				label.setColor(data.getColor());
			actor = label;
			break;
		default:
			break;
		}
		if(actor!=null){
			setBasicInformation(actor ,data);
			group.addActor(actor);
		}
		for (ObjectData childData : data.getChilds()) {
			creatBaBy(childData,childGroup,atlas);
		}
	}
	/**
	 * 设置基础信息
	 */
	private void setBasicInformation(Actor actor,ObjectData data){
		actor.setName(data.getName());
		if(data.getPosition()!=null)
			actor.setPosition(data.getPosition().getX(), data.getPosition().getY());
	}
	public void setAnchorType(VLabel label, AnchorType anchorType) {
		switch (anchorType) {
		case center_center:
			label.setAlignment(Align.center);
			break;
		case center_left:
			label.setAlignment(Align.left);
			break;
		case center_right:
			label.setAlignment(Align.right);
			break;
		case bottom_Left:
			label.setAlignment(Align.bottomLeft);
			break;
		case bottom_Right:
			label.setAlignment(Align.bottomRight);
			break;
		case top_Left:
			label.setAlignment(Align.topLeft);
			break;
		case top_Right:
			label.setAlignment(Align.topRight);
			break;
		default:
			break;
		}
	}

}
