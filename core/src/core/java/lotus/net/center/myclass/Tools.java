package lotus.net.center.myclass;

import java.nio.ByteBuffer;
import java.util.Stack;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Base64Coder;

public class Tools
{
	
	public final static Color color = new Color(253f/255,227f/255,90f/255,1);
    private final static String digths = "0123456789~!c^&d@$%*(bh)-wai=jk_+rs{}t#u[]ef;op':<mv>gl?nq/";
    private final static int key = 21;
    
    public static TextureRegion setRegionFilter(TextureRegion textureRegion)
    {
        textureRegion.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return textureRegion;
    }
    
    public static Texture setTextureFilter(Texture texture)
    {
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return texture;
    }
    
    public static String array1ToString(int[] array1)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array1.length; i++)
        {
            sb.append(baseNum(String.valueOf(array1[i] + i * 77), 10, 49) + "z");
        }
        return sb.toString();
    }
    
    public static String array2ToString(int[][] array2)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array2.length; i++)
        {
            sb.append(array1ToString(array2[i]) + "x");
        }
        return sb.toString();
    }
    
    public static int[] stringToArray1(String str)
    {
        String[] items = str.split("z");
        int[] array = new int[items.length];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = Integer.parseInt(baseNum(items[i], 49, 10)) - i * 77;
        }
        return array;
    }
    
    public static int[][] stringToArray2(String str)
    {
        String[] items = str.split("x");
        int[][] array2 = new int[items.length][];
        for (int i = 0; i < items.length; i++)
        {
            array2[i] = stringToArray1(items[i]);
        }
        return array2;
    }
	public static Texture getFrameBufferTexture () {
		final int w = Gdx.graphics.getWidth();
		final int h = Gdx.graphics.getHeight();
		Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);
		final int potW = MathUtils.nextPowerOfTwo(w);
		final int potH = MathUtils.nextPowerOfTwo(h);

		final Pixmap pixmap = new Pixmap(potW, potH, Format.RGBA8888);
		ByteBuffer pixels = pixmap.getPixels();
		Gdx.gl.glReadPixels(0, 0, potW, potH, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);

		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}
    /**
     * to destbase
     * @return
     */
    private static String baseNum(String num, int srcBase, int destBase)
    {
        if (srcBase == destBase)
        {
            return num;
        }
        char[] chars = num.toCharArray();
        int len = chars.length;
        if (destBase != 10)
        {// to 10
            num = baseNum(num, srcBase, 10);
        }
        else
        {
            int n = 0;
            for (int i = len - 1; i >= 0; i--)
            {
                n += digths.indexOf(chars[i]) * Math.pow(srcBase, len - i - 1);
            }
            return n + "";
        }
        return baseString(Integer.valueOf(num), destBase);
    }
    
    /**
     * number to String
     */
    private static String baseString(int num, int base)
    {
        StringBuffer str = new StringBuffer("");
        Stack<Character> s = new Stack<Character>();
        while (num != 0)
        {
            s.push(digths.charAt(num % base));
            num /= base;
        }
        while (!s.isEmpty())
        {
            str.append(s.pop());
        }
        return str.toString();
    }
    public static Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        return pixmap;
    }
    public static void drawNumberRegionForCenter(Batch batch,TextureRegion[] regions,int number, int x, int y,float scale,boolean isAdd0){
    	char[] numberChars = ((number>=10?"":(isAdd0?"0":""))+String.valueOf(number)).toCharArray();
    	for (int i = 0; i < numberChars.length; i++) {
    		int a = Integer.parseInt(String.valueOf(numberChars[i]));
    		batch.draw(regions[a], x + ((float)(2 * i - numberChars.length) / 2) * regions[a].getRegionWidth()*scale,
    				y-regions[a].getRegionHeight()*scale/2,regions[a].getRegionWidth()*scale,regions[a].getRegionHeight()*scale);
		}
    }
    
    public static void addNumberRegionForCenter(Group group,TextureRegion regions[],float number, int x, int y,float scale,boolean isgang){
    	int hand = (int) number;
    	float end = number - hand;
    	int endNumber = (int) (end*1000);
    	String endString = String.valueOf(endNumber);
    	if(endString.length()==1){
    		endString = endString+"00";
    	}else if(endString.length() == 2){
    		endString = endString +"0";
    	}
    	char[] numberChars = (hand+"b"+endString+"a").toCharArray();
    	for (int i = 0; i < numberChars.length; i++) {
    		int a = 0;
    		if(numberChars[i]=='a'){
    			if(isgang){
    				a = 12;
    			}else{
    				a = 10;
    			}
    		}else if(numberChars[i] == 'b'){
    				a = 11;
    			}else{
    			a = Integer.parseInt(String.valueOf(numberChars[i]));
    		}
			Image image = new Image(regions[a]);
			image.setScale(scale);
			image.setPosition(x + ((float)(2 * i - numberChars.length) / 2) * regions[a].getRegionWidth()*scale,y-regions[a].getRegionHeight()*scale/2);
			group.addActor(image);
		}
    }
	public static void addNumberRegionForLeft(Group group,TextureRegion regions[],float number, int x, int y,float scale){
		int hand = (int) number;
		float end = number - hand;
		int endNumber = (int) (end*1000);
		String endString = String.valueOf(endNumber);
		if(endString.length()==1){
			endString = endString+"00";
		}else if(endString.length() == 2){
			endString = endString +"0";
		}
		char[] numberChars = (hand+"b"+endString+"a").toCharArray();
		for (int i = 0; i < numberChars.length; i++) {
			int a = 0;
			if(numberChars[i]=='a'){
				a = 10;
			}else if(numberChars[i] == 'b'){
				a = 11;
			}else{
				a = Integer.parseInt(String.valueOf(numberChars[i]));
			}
			Image image = new Image(regions[a]);
			image.setScale(scale);
			image.setPosition(x + i * regions[a].getRegionWidth()*scale,y);
			group.addActor(image);
		}
	}
	public static void addNumberRegionForCenter(Group group,TextureRegion regions[],int number, int x, int y,float scale,boolean isAdd0){
		char[] numberChars = ((number>=10?"":(isAdd0?"0":""))+String.valueOf(number)).toCharArray();
		for (int i = 0; i < numberChars.length; i++) {
			int a = Integer.parseInt(String.valueOf(numberChars[i]));
			Image image = new Image(regions[a]);
			image.setScale(scale);
			image.setTouchable(Touchable.disabled);
			image.setPosition(x + ((float)(2 * i - numberChars.length) / 2) * regions[a].getRegionWidth()*scale,y-regions[a].getRegionHeight()*scale/2);
			group.addActor(image);
		}
	}
	public static void addNumberRegionForLeft(Group group,TextureRegion regions[],int number, int x, int y,float scale,boolean isAdd0){
		char[] numberChars = ((number>=10?"":(isAdd0?"0":""))+String.valueOf(number)).toCharArray();
		for (int i = 0; i < numberChars.length; i++) {
			int a = Integer.parseInt(String.valueOf(numberChars[i]));
			Image image = new Image(regions[a]);
			image.setScale(scale);
			image.setPosition(x + i * regions[a].getRegionWidth()*scale,y);
			group.addActor(image);
		}
	}
	public static void addNumberRegionForCenter(Group group,TextureRegion region,int number, int x, int y,float scale,boolean isAdd0){
		TextureRegion regions[] = region.split((int)region.getRegionWidth()/10, (int)region.getRegionHeight())[0];
		char[] numberChars = ((number>=10?"":(isAdd0?"0":""))+String.valueOf(number)).toCharArray();
		for (int i = 0; i < numberChars.length; i++) {
			int a = Integer.parseInt(String.valueOf(numberChars[i]));
			Image image = new Image(regions[a]);
			image.setScale(scale);
			image.setPosition(x + ((float)(2 * i - numberChars.length) / 2) * regions[a].getRegionWidth()*scale,y-regions[a].getRegionHeight()*scale/2);
			group.addActor(image);
		}
	}
	public  void addNumberRegionForLeft(Group group,TextureRegion region,int number, int x, int y,float scale,boolean isAdd0){
		TextureRegion regions[] = region.split((int)region.getRegionWidth()/10, (int)region.getRegionHeight())[0];
		char[] numberChars = ((number>=10?"":(isAdd0?"0":""))+String.valueOf(number)).toCharArray();
		for (int i = 0; i < numberChars.length; i++) {
			int a = Integer.parseInt(String.valueOf(numberChars[i]));
			Image image = new Image(regions[a]);
			image.setScale(scale);
			image.setPosition(x + i * regions[a].getRegionWidth()*scale,y);
			group.addActor(image);
		}
	}

	//加密
	public static String encodeString(String content){
		//获取用户输入
		//讲获取的字符串转成字符数组
		char[] c = content.toCharArray();
		//使用for循环给字符数组加密
		for(int i=0;i<c.length;i++){
			c[i] = (char)(c[i]^key);
		}
		return Base64Coder.encodeString(new String(c),false);
	}

	//解密
	public static String decodeString(String content){
		content = Base64Coder.decodeString(content,false);
		char[] c = content.toCharArray();
		for(int i=0;i<c.length;i++){
			c[i] = (char)(c[i]^key);
		}
		return new String(c);
	}
	public static void creatEncodeFile(String address){
		FileHandle original_file = Gdx.files.absolute(address);
		FileHandle encode_file = Gdx.files.absolute("/Users/luoyi/android/libgdx/Github/laoduandian.github.io/ad/ads/"+original_file.name());
		encode_file.writeString(encodeString(original_file.readString()),false);
	}
}
