import org.simpleyaml.configuration.file.YamlFile;

public class ConfigValue<T> {
    private final String path;
    private final T defaultValue;

    public ConfigValue(String path, T defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    @SuppressWarnings("unchecked")
    public T get(YamlFile file, boolean[] saveFlag) {
        if (!file.contains(path)) {
            file.set(path, defaultValue);
            saveFlag[0] = true;
            return defaultValue;
        }
        return (T) file.get(path);
    }

    public String getPath() {
        return path;
    }

    public T getDefault() {
        return defaultValue;
    }
}
