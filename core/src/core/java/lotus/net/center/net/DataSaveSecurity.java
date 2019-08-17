package lotus.net.center.net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;


public class DataSaveSecurity {
    private Save save;
    private FileHandle file;

    public DataSaveSecurity() {

        file = Gdx.files.local("bin/scores.json");
        save = getSave();
    }

    private Save getSave() {
        Save save = new Save();

        if (file.exists()) {
            Json json = new Json();
            // 读取文件，并且解密
            save = json.fromJson(Save.class, Base64Coder.decodeString(file.readString()));
        }
        return save;
    }

    public void saveToJson() {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        file.writeString(Base64Coder.encodeString(json.prettyPrint(save)), false);
        file.writeString(json.toJson(save),false);
    }

    public void saveDataValue(String key, Object object){
        save.data.put(key, object);
        saveToJson(); // 立即保存数据

    }

    public Object loadDataValue(String key, Class<Object> type){
        // 如果包含key的数据则返回数据，否则返回null
        if(save.data.containsKey(key))
            return save.data.get(key);
        else
            return null;
    }
    /** 根据需要T替换要读取的类型
     * public <t> T loadDataValue(String key, Class type){
     if(save.data.containsKey(key))return (T) save.data.get(key);
     else return null;   //this if() avoids exception, but check for null on load.

     }
     */
    private static class Save {
        public ObjectMap data = new ObjectMap();
    }
}
