import org.example.entity.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import static org.junit.Assert.*;
public class StudentTest {


    @Test
    public void whenMatches9DigitsNumber_thenCorrect() {
        Pattern pattern = Pattern.compile("^\\d{9}");
        Matcher matcher = pattern.matcher("883430263");
        assertTrue(matcher.matches());
    }

}
