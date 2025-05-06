import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;

public abstract class Config {
    protected final YamlFile config;

    public Config(File file) throws IOException {
        this.config = new YamlFile(file);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        config.loadWithComments();
        config.save();
        load();
    }

    protected abstract void load() throws IOException;
    private boolean modified = false;

    public void save() throws IOException {
        if (modified) {
            config.save();
            modified = false;
        }
    }

    protected void setDefault(String path, Object value) {
        if (!config.contains(path)) {
            config.set(path, value);
            modified = true;
        }
    }

    protected void setDefault(String path, Object value, String comment) {
        if (!config.contains(path)) {
            config.set(path, value);
            config.setComment(path, comment);
            modified = true;
        }
    }

    public void reload() throws IOException {
        config.load();
        load();
    }

    public YamlFile getYaml() {
        return config;
    }
}
