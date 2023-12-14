import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class MusicPlayerTest {
    @Test
    void playMusic_ValidFilePath_NoExceptionThrown() {
        String filePath = "Pokémon Theme Song (Music Video).wav";
        try {
            MusicPlayer.playMusic(filePath);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void playMusic_InvalidFilePath_ExceptionThrown() {
        String filePath = "path/to/invalid/audio/file.txt";
        Assertions.assertThrows(FileNotFoundException.class, () -> MusicPlayer.playMusic(filePath));
    }
}

