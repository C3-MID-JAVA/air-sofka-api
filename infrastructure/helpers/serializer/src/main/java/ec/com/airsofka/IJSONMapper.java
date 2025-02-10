package ec.com.airsofka;

public interface IJSONMapper {
    String writeToJson(Object obj);
    Object readFromJson(String json, Class<?> clazz);
}
