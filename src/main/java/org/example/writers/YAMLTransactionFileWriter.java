package org.example.writers;

import org.example.Transaction;
import org.example.TransactionListWrapper;
import org.example.writers.util.TransactionFileWriter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class YAMLTransactionFileWriter implements TransactionFileWriter {
    @Override
    public void write(String filePath, List<Transaction> transactions) throws IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Representer representer = new Representer();
        Yaml yaml = new Yaml(representer, options);
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(new TransactionListWrapper(transactions), writer);
        }
    }
}