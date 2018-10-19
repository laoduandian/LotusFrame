package lotus.net.center.myclass.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class GrayscaleShader {
	 static String vertexShader = "attribute vec4 a_position;\n" +
	            "attribute vec4 a_color;\n" +
	            "attribute vec2 a_texCoord0;\n" +
	            "\n" +
	            "uniform mat4 u_projTrans;\n" +
	            "\n" +
	            "varying vec4 v_color;\n" +
	            "varying vec2 v_texCoords;\n" +
	            "\n" +
	            "void main() {\n" +
	            "    v_color = a_color;\n" +
	            "    v_texCoords = a_texCoord0;\n" +
	            "    gl_Position = u_projTrans * a_position;\n" +
	            "}";

	    static String fragmentShader = "#ifdef GL_ES\n" +
	            "    precision mediump float;\n" +
	            "#endif\n" +
	            "\n" +
	            "varying vec4 v_color;\n" +
	            "varying vec2 v_texCoords;\n" +
	            "uniform sampler2D u_texture;\n" +
	            "\n" +
	            "void main() {\n" +
	            "  vec4 c = v_color * texture2D(u_texture, v_texCoords);\n" +
	            "  float grey = (c.r + c.g + c.b) / 3.0;\n" +
	            "  gl_FragColor = vec4(grey, grey, grey, c.a);\n" +
	            "}";

	    public static ShaderProgram grayscaleShader = new ShaderProgram(vertexShader,
	            fragmentShader);



	    static String click_vert = "attribute vec4 a_position;\n" +
				"attribute vec4 a_color;\n" +
				"attribute vec2 a_texCoord0;\n" +
				"\n" +
				"\n" +
				"uniform mat4 u_projTrans;\n" +
				"varying vec4 v_color;\n" +
				"varying vec2 v_texCoords;\n" +
				"varying vec4 v_position;\n" +
				"\n" +
				"void main()\n" +
				"{\n" +
				"    v_color = a_color;\n" +
				"\tv_color.a = v_color.a * (255.0/254.0);\n" +
				"\tv_texCoords = a_texCoord0;\n" +
				"\tv_position = a_position;\n" +
				"\tgl_Position =  u_projTrans * a_position;\n" +
				"}";
	static String click_frag ="#ifdef GL_ES\n" +
			"precision mediump float;\n" +
			"#endif\n" +
			"\n" +
			"varying vec2 v_texCoords;\n" +
			"varying vec4 v_color;\n" +
			"uniform sampler2D u_texture;\n" +
			"uniform vec2 u_lightPosition;\n" +
			"uniform vec4 u_lightColor;\n" +
			"uniform float u_lightRange;\n" +
			"\n" +
			"const float rad = 0.2;\n" +
			"\n" +
			"vec4 getRenderColor(vec2 texPos, vec2 lightPos, float lightRange,vec4 v_texture)\n" +
			"{\n" +
			"    vec2 pos = texPos - lightPos;\n" +
			"    float d = length(pos);//顶点与灯的距离\n" +
			"\n" +
			"\n" +
			"    float rgb = lightRange;\n" +
			"    rgb = clamp(rgb, 0.0, 1.0);//clamp意义为 min(max(a, b), c);将a的大小限制在b,c之间， 1-rgb是将颜色反转\n" +
			"    return vec4((u_lightColor+rgb).rgb*v_texture.rgb,v_texture.a);\n" +
			"\n" +
			"}\n" +
			"void main()\n" +
			"{\n" +
			"    vec4 v = texture2D(u_texture, v_texCoords);\n" +
			"    vec4 color =  getRenderColor(v_texCoords, u_lightPosition, u_lightRange,v);//灯光颜色与灯光强度混合\n" +
			"    color = clamp(color, 0.0, 1.0);\n" +
			"    gl_FragColor = color * v_color ;//纹理与灯光混合\n" +
			"}";
	public static ShaderProgram clickShader = new ShaderProgram(click_vert
                ,click_frag);
}
