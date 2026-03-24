package pe.edu.fineflow.common.util;
import java.util.UUID;
/** Generador centralizado de UUIDs para PKs. */
public final class UuidGenerator {
    private UuidGenerator() {}
    public static String generate() { return UUID.randomUUID().toString(); }
}
