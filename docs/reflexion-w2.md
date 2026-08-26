# Reflexion zu Woche 2

## 1. Erweiterung um den Gerätetyp Tablet

Die Tablet-Klasse ist inzwischen schon mitimplementiert. Im Nachhinein sieht man
deshalb ziemlich gut, wie viel dafür tatsächlich geändert werden musste:

1. Die neue Klasse `Tablet` wurde angelegt. Dort werden auch
   `calculateResidualValue()` und `getInventoryLine()` überschrieben.
2. In `Main` wurden Tablet-Objekte zum Testen und für die Ausgabe ergänzt.

`AssetReport` musste überhaupt nicht angepasst werden. Der Report arbeitet einfach mit
`Asset`-Objekten und ruft deren Methoden auf. Welche konkrete Methode ausgeführt wird,
entscheidet Java zur Laufzeit anhand des tatsächlichen Gerätetyps. Genau das ist hier der
Vorteil der Polymorphie.

**Zeitmessung aus Woche 1: 12 Minuten**

## 2. Alternative mit einer Typ-Spalte und `switch`

Ohne Polymorphie müsste `totalResidualValue()` selbst prüfen, um welchen Gerätetyp es sich
handelt. Vereinfacht könnte das ungefähr so aussehen:

```java
public BigDecimal totalResidualValue(List<Asset> assets) {
    BigDecimal total = BigDecimal.ZERO;

    for (Asset asset : assets) {
        int usefulLifeYears;

        switch (asset.getType()) {
            case NOTEBOOK:
            case TABLET:
                usefulLifeYears = 3;
                break;
            case MONITOR:
            case PRINTER:
                usefulLifeYears = 5;
                break;
            case SERVER:
                usefulLifeYears = 7;
                break;
            default:
                throw new IllegalArgumentException("Unknown asset type");
        }

        total = total.add(
                calculateLinearResidualValue(asset, usefulLifeYears)
        );
    }

    return total;
}
```

`getType()` und die Hilfsmethode aus diesem Beispiel gibt es im aktuellen Projekt nicht;
der Code zeigt nur, wie eine Lösung mit Typabfrage ungefähr aufgebaut wäre.

Diese Variante hätte vor allem zwei Nachteile:

- Bei jedem neuen Gerätetyp müsste der `switch` wieder erweitert werden. Vergisst man einen
  Fall, fällt der Fehler möglicherweise erst während der Ausführung auf.
- `AssetReport` müsste die Abschreibungsregeln aller Gerätetypen kennen.

Mit Polymorphie bleibt die Regel dagegen in der jeweiligen Geräteklasse. `AssetReport`
muss nur wissen, dass jedes `Asset` einen Restwert berechnen kann.