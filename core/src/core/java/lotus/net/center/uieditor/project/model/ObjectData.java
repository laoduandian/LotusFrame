package lotus.net.center.uieditor.project.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

import lotus.net.center.uieditor.project.widget.AnchorType;
import lotus.net.center.uieditor.project.widget.WidgetType;


/**
 * 控件
 * 
 */
public class ObjectData implements Cloneable{
	private String name;
	private Boolean Visible;
	private int index;
	private WidgetType widgetType;
	private Position position;
	private Size size;
	private Scale scale;
	private Origin origin;
	private Color color;
	private FileData fileData;
	private boolean DebugEnable;
	private Touchable touchable;
	private Array<ObjectData> childs;
	/** Optional. */
	private FileData up, down, over, checked, checkedOver, disabled;
	/** Optional. */
	private float pressedOffsetX, pressedOffsetY, unpressedOffsetX, unpressedOffsetY, checkedOffsetX, checkedOffsetY;
	private String imageNumberContent;
	private Anchor anchor;
	boolean FlipX,FlipY;
	private int fontSize;
	private AnchorType anchorType;
	private String labelContent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageNumberContent() {
		return imageNumberContent;
	}
	public void setImageNumberContent(String imageNumberContent) {
		this.imageNumberContent = imageNumberContent;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public void setPosition(float x, float y) {
		if(this.position == null)
			this.position = new Position();
		position.setPosition(x, y);
	}
	public WidgetType getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(WidgetType widgetType) {
		this.widgetType = widgetType;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public void setSize(float width, float height){
		if(size == null)
			size = new Size();
		size.setSize(width,height);
	}
	public Scale getScale() {
		return scale;
	}
	public void setScale(Scale scale) {
		this.scale = scale;
	}
	public void setScale(float scaleX, float scaleY){
		if(scale == null)
			scale = new Scale();
		scale.setScaleX(scaleX);
		scale.setScaleY(scaleY);
	}
	public FileData getFileData() {
		return fileData;
	}
	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Boolean getVisible() {
		return Visible;
	}
	public void setVisible(Boolean visible) {
		Visible = visible;
	}
	public boolean isDebugEnable() {
		return DebugEnable;
	}
	public void setDebugEnable(boolean debugEnable) {
		DebugEnable = debugEnable;
	}
	public Array<ObjectData> getChilds() {
		if(childs==null)
			childs = new Array<ObjectData>();
		return childs;
	}
	public void setChilds(Array<ObjectData> childs) {
		this.childs = childs;
	}
	public FileData getUp() {
		return up;
	}
	public void setUp(FileData up) {
		this.up = up;
	}
	public FileData getDown() {
		return down;
	}
	public void setDown(FileData down) {
		this.down = down;
	}
	public FileData getOver() {
		return over;
	}
	public void setOver(FileData over) {
		this.over = over;
	}
	public FileData getChecked() {
		return checked;
	}
	public void setChecked(FileData checked) {
		this.checked = checked;
	}
	public FileData getCheckedOver() {
		return checkedOver;
	}
	public void setCheckedOver(FileData checkedOver) {
		this.checkedOver = checkedOver;
	}
	public FileData getDisabled() {
		return disabled;
	}
	public void setDisabled(FileData disabled) {
		this.disabled = disabled;
	}
	public float getPressedOffsetX() {
		return pressedOffsetX;
	}
	public void setPressedOffsetX(float pressedOffsetX) {
		this.pressedOffsetX = pressedOffsetX;
	}
	public float getPressedOffsetY() {
		return pressedOffsetY;
	}
	public void setPressedOffsetY(float pressedOffsetY) {
		this.pressedOffsetY = pressedOffsetY;
	}
	public float getUnpressedOffsetX() {
		return unpressedOffsetX;
	}
	public void setUnpressedOffsetX(float unpressedOffsetX) {
		this.unpressedOffsetX = unpressedOffsetX;
	}
	public float getUnpressedOffsetY() {
		return unpressedOffsetY;
	}
	public void setUnpressedOffsetY(float unpressedOffsetY) {
		this.unpressedOffsetY = unpressedOffsetY;
	}
	public float getCheckedOffsetX() {
		return checkedOffsetX;
	}
	public void setCheckedOffsetX(float checkedOffsetX) {
		this.checkedOffsetX = checkedOffsetX;
	}
	public float getCheckedOffsetY() {
		return checkedOffsetY;
	}
	public void setCheckedOffsetY(float checkedOffsetY) {
		this.checkedOffsetY = checkedOffsetY;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public void setOrigin(float x, float y) {
		if(origin == null)
			origin = new Origin();
		origin.setOrigin(x, y);
	}
	public Anchor getAnchor() {
		if(anchor == null)
			anchor = new Anchor();
		return anchor;
	}
	public void setAnchor(float x, float y) {
		if(anchor == null)
			anchor = new Anchor();
		anchor.setAnchor(x, y);
	}
	public Touchable getTouchable() {
		return touchable;
	}
	public void setTouchable(Touchable touchable) {
		this.touchable = touchable;
	}
	public boolean isFlipX() {
		return FlipX;
	}
	public void setFlipX(Boolean isFlipX) {
		this.FlipX = isFlipX;
	}
	public boolean isFlipY() {
		return FlipY;
	}
	public void setFlipY(Boolean isFlipY) {
		this.FlipY = isFlipY;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public AnchorType getAnchorType() {
		return anchorType;
	}
	public void setAnchorType(AnchorType anchorType) {
		this.anchorType = anchorType;
	}
	public String getLabelContent() {
		return labelContent;
	}
	public void setLabelContent(String labelContent) {
		this.labelContent = labelContent;
	}
}
