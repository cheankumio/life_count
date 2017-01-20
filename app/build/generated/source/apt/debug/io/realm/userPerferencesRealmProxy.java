package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lifetime.apper.klc.lifetime.Auxiliary.userPerferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class userPerferencesRealmProxy extends userPerferences
    implements RealmObjectProxy {

    static final class userPerferencesColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long NameIndex;
        public final long maxSecIndex;
        public final long bornSecIndex;
        public final long colorIndex;
        public final long tmp2Index;
        public final long tmp3Index;
        public final long tmp4Index;
        public final long tmp5Index;
        public final long tmp6Index;
        public final long tmp7Index;
        public final long tmp8Index;

        userPerferencesColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(12);
            this.idIndex = getValidColumnIndex(path, table, "userPerferences", "id");
            indicesMap.put("id", this.idIndex);

            this.NameIndex = getValidColumnIndex(path, table, "userPerferences", "Name");
            indicesMap.put("Name", this.NameIndex);

            this.maxSecIndex = getValidColumnIndex(path, table, "userPerferences", "maxSec");
            indicesMap.put("maxSec", this.maxSecIndex);

            this.bornSecIndex = getValidColumnIndex(path, table, "userPerferences", "bornSec");
            indicesMap.put("bornSec", this.bornSecIndex);

            this.colorIndex = getValidColumnIndex(path, table, "userPerferences", "color");
            indicesMap.put("color", this.colorIndex);

            this.tmp2Index = getValidColumnIndex(path, table, "userPerferences", "tmp2");
            indicesMap.put("tmp2", this.tmp2Index);

            this.tmp3Index = getValidColumnIndex(path, table, "userPerferences", "tmp3");
            indicesMap.put("tmp3", this.tmp3Index);

            this.tmp4Index = getValidColumnIndex(path, table, "userPerferences", "tmp4");
            indicesMap.put("tmp4", this.tmp4Index);

            this.tmp5Index = getValidColumnIndex(path, table, "userPerferences", "tmp5");
            indicesMap.put("tmp5", this.tmp5Index);

            this.tmp6Index = getValidColumnIndex(path, table, "userPerferences", "tmp6");
            indicesMap.put("tmp6", this.tmp6Index);

            this.tmp7Index = getValidColumnIndex(path, table, "userPerferences", "tmp7");
            indicesMap.put("tmp7", this.tmp7Index);

            this.tmp8Index = getValidColumnIndex(path, table, "userPerferences", "tmp8");
            indicesMap.put("tmp8", this.tmp8Index);

            setIndicesMap(indicesMap);
        }
    }

    private final userPerferencesColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("Name");
        fieldNames.add("maxSec");
        fieldNames.add("bornSec");
        fieldNames.add("color");
        fieldNames.add("tmp2");
        fieldNames.add("tmp3");
        fieldNames.add("tmp4");
        fieldNames.add("tmp5");
        fieldNames.add("tmp6");
        fieldNames.add("tmp7");
        fieldNames.add("tmp8");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    userPerferencesRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (userPerferencesColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public int getId() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.idIndex);
    }

    @Override
    public void setId(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.NameIndex);
    }

    @Override
    public void setName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.NameIndex);
            return;
        }
        row.setString(columnInfo.NameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public long getMaxSec() {
        realm.checkIfValid();
        return (long) row.getLong(columnInfo.maxSecIndex);
    }

    @Override
    public void setMaxSec(long value) {
        realm.checkIfValid();
        row.setLong(columnInfo.maxSecIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public long getBornSec() {
        realm.checkIfValid();
        return (long) row.getLong(columnInfo.bornSecIndex);
    }

    @Override
    public void setBornSec(long value) {
        realm.checkIfValid();
        row.setLong(columnInfo.bornSecIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getcolor() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.colorIndex);
    }

    @Override
    public void setcolor(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.colorIndex);
            return;
        }
        row.setString(columnInfo.colorIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp2() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp2Index);
    }

    @Override
    public void setTmp2(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp2Index);
            return;
        }
        row.setString(columnInfo.tmp2Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp3() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp3Index);
    }

    @Override
    public void setTmp3(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp3Index);
            return;
        }
        row.setString(columnInfo.tmp3Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp4() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp4Index);
    }

    @Override
    public void setTmp4(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp4Index);
            return;
        }
        row.setString(columnInfo.tmp4Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp5() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp5Index);
    }

    @Override
    public void setTmp5(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp5Index);
            return;
        }
        row.setString(columnInfo.tmp5Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp6() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp6Index);
    }

    @Override
    public void setTmp6(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp6Index);
            return;
        }
        row.setString(columnInfo.tmp6Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp7() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp7Index);
    }

    @Override
    public void setTmp7(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp7Index);
            return;
        }
        row.setString(columnInfo.tmp7Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTmp8() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tmp8Index);
    }

    @Override
    public void setTmp8(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.tmp8Index);
            return;
        }
        row.setString(columnInfo.tmp8Index, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_userPerferences")) {
            Table table = transaction.getTable("class_userPerferences");
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "Name", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "maxSec", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "bornSec", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "color", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp2", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp3", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp4", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp5", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp6", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp7", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tmp8", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_userPerferences");
    }

    public static userPerferencesColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_userPerferences")) {
            Table table = transaction.getTable("class_userPerferences");
            if (table.getColumnCount() != 12) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 12 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 12; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final userPerferencesColumnInfo columnInfo = new userPerferencesColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("Name")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'Name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("Name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'Name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.NameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'Name' is required. Either set @Required to field 'Name' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("maxSec")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'maxSec' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("maxSec") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'maxSec' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.maxSecIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'maxSec' does support null values in the existing Realm file. Use corresponding boxed type for field 'maxSec' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("bornSec")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'bornSec' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("bornSec") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'bornSec' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.bornSecIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'bornSec' does support null values in the existing Realm file. Use corresponding boxed type for field 'bornSec' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("color")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'color' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("color") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'color' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.colorIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'color' is required. Either set @Required to field 'color' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp2")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp2' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp2") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp2' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp2Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp2' is required. Either set @Required to field 'tmp2' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp3")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp3' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp3") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp3' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp3Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp3' is required. Either set @Required to field 'tmp3' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp4")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp4' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp4") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp4' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp4Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp4' is required. Either set @Required to field 'tmp4' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp5")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp5' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp5") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp5' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp5Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp5' is required. Either set @Required to field 'tmp5' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp6")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp6' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp6") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp6' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp6Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp6' is required. Either set @Required to field 'tmp6' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp7")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp7' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp7") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp7' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp7Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp7' is required. Either set @Required to field 'tmp7' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("tmp8")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tmp8' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tmp8") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tmp8' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tmp8Index)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'tmp8' is required. Either set @Required to field 'tmp8' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The userPerferences class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_userPerferences";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static userPerferences createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        userPerferences obj = null;
        if (update) {
            Table table = realm.getTable(userPerferences.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("id")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new userPerferencesRealmProxy(realm.schema.getColumnInfo(userPerferences.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = realm.createObject(userPerferences.class, null);
                } else {
                    obj = realm.createObject(userPerferences.class, json.getInt("id"));
                }
            } else {
                obj = realm.createObject(userPerferences.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
            } else {
                obj.setId((int) json.getInt("id"));
            }
        }
        if (json.has("Name")) {
            if (json.isNull("Name")) {
                obj.setName(null);
            } else {
                obj.setName((String) json.getString("Name"));
            }
        }
        if (json.has("maxSec")) {
            if (json.isNull("maxSec")) {
                throw new IllegalArgumentException("Trying to set non-nullable field maxSec to null.");
            } else {
                obj.setMaxSec((long) json.getLong("maxSec"));
            }
        }
        if (json.has("bornSec")) {
            if (json.isNull("bornSec")) {
                throw new IllegalArgumentException("Trying to set non-nullable field bornSec to null.");
            } else {
                obj.setBornSec((long) json.getLong("bornSec"));
            }
        }
        if (json.has("color")) {
            if (json.isNull("color")) {
                obj.setcolor(null);
            } else {
                obj.setcolor((String) json.getString("color"));
            }
        }
        if (json.has("tmp2")) {
            if (json.isNull("tmp2")) {
                obj.setTmp2(null);
            } else {
                obj.setTmp2((String) json.getString("tmp2"));
            }
        }
        if (json.has("tmp3")) {
            if (json.isNull("tmp3")) {
                obj.setTmp3(null);
            } else {
                obj.setTmp3((String) json.getString("tmp3"));
            }
        }
        if (json.has("tmp4")) {
            if (json.isNull("tmp4")) {
                obj.setTmp4(null);
            } else {
                obj.setTmp4((String) json.getString("tmp4"));
            }
        }
        if (json.has("tmp5")) {
            if (json.isNull("tmp5")) {
                obj.setTmp5(null);
            } else {
                obj.setTmp5((String) json.getString("tmp5"));
            }
        }
        if (json.has("tmp6")) {
            if (json.isNull("tmp6")) {
                obj.setTmp6(null);
            } else {
                obj.setTmp6((String) json.getString("tmp6"));
            }
        }
        if (json.has("tmp7")) {
            if (json.isNull("tmp7")) {
                obj.setTmp7(null);
            } else {
                obj.setTmp7((String) json.getString("tmp7"));
            }
        }
        if (json.has("tmp8")) {
            if (json.isNull("tmp8")) {
                obj.setTmp8(null);
            } else {
                obj.setTmp8((String) json.getString("tmp8"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static userPerferences createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        userPerferences obj = realm.createObject(userPerferences.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
                } else {
                    obj.setId((int) reader.nextInt());
                }
            } else if (name.equals("Name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setName(null);
                } else {
                    obj.setName((String) reader.nextString());
                }
            } else if (name.equals("maxSec")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field maxSec to null.");
                } else {
                    obj.setMaxSec((long) reader.nextLong());
                }
            } else if (name.equals("bornSec")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field bornSec to null.");
                } else {
                    obj.setBornSec((long) reader.nextLong());
                }
            } else if (name.equals("color")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setcolor(null);
                } else {
                    obj.setcolor((String) reader.nextString());
                }
            } else if (name.equals("tmp2")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp2(null);
                } else {
                    obj.setTmp2((String) reader.nextString());
                }
            } else if (name.equals("tmp3")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp3(null);
                } else {
                    obj.setTmp3((String) reader.nextString());
                }
            } else if (name.equals("tmp4")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp4(null);
                } else {
                    obj.setTmp4((String) reader.nextString());
                }
            } else if (name.equals("tmp5")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp5(null);
                } else {
                    obj.setTmp5((String) reader.nextString());
                }
            } else if (name.equals("tmp6")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp6(null);
                } else {
                    obj.setTmp6((String) reader.nextString());
                }
            } else if (name.equals("tmp7")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp7(null);
                } else {
                    obj.setTmp7((String) reader.nextString());
                }
            } else if (name.equals("tmp8")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTmp8(null);
                } else {
                    obj.setTmp8((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static userPerferences copyOrUpdate(Realm realm, userPerferences object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        userPerferences realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(userPerferences.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, object.getId());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new userPerferencesRealmProxy(realm.schema.getColumnInfo(userPerferences.class));
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static userPerferences copy(Realm realm, userPerferences newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        userPerferences realmObject = realm.createObject(userPerferences.class, newObject.getId());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setId(newObject.getId());
        realmObject.setName(newObject.getName());
        realmObject.setMaxSec(newObject.getMaxSec());
        realmObject.setBornSec(newObject.getBornSec());
        realmObject.setcolor(newObject.getcolor());
        realmObject.setTmp2(newObject.getTmp2());
        realmObject.setTmp3(newObject.getTmp3());
        realmObject.setTmp4(newObject.getTmp4());
        realmObject.setTmp5(newObject.getTmp5());
        realmObject.setTmp6(newObject.getTmp6());
        realmObject.setTmp7(newObject.getTmp7());
        realmObject.setTmp8(newObject.getTmp8());
        return realmObject;
    }

    public static userPerferences createDetachedCopy(userPerferences realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<userPerferences> cachedObject = (CacheData) cache.get(realmObject);
        userPerferences standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new userPerferences();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setId(realmObject.getId());
        standaloneObject.setName(realmObject.getName());
        standaloneObject.setMaxSec(realmObject.getMaxSec());
        standaloneObject.setBornSec(realmObject.getBornSec());
        standaloneObject.setcolor(realmObject.getcolor());
        standaloneObject.setTmp2(realmObject.getTmp2());
        standaloneObject.setTmp3(realmObject.getTmp3());
        standaloneObject.setTmp4(realmObject.getTmp4());
        standaloneObject.setTmp5(realmObject.getTmp5());
        standaloneObject.setTmp6(realmObject.getTmp6());
        standaloneObject.setTmp7(realmObject.getTmp7());
        standaloneObject.setTmp8(realmObject.getTmp8());
        return standaloneObject;
    }

    static userPerferences update(Realm realm, userPerferences realmObject, userPerferences newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setName(newObject.getName());
        realmObject.setMaxSec(newObject.getMaxSec());
        realmObject.setBornSec(newObject.getBornSec());
        realmObject.setcolor(newObject.getcolor());
        realmObject.setTmp2(newObject.getTmp2());
        realmObject.setTmp3(newObject.getTmp3());
        realmObject.setTmp4(newObject.getTmp4());
        realmObject.setTmp5(newObject.getTmp5());
        realmObject.setTmp6(newObject.getTmp6());
        realmObject.setTmp7(newObject.getTmp7());
        realmObject.setTmp8(newObject.getTmp8());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("userPerferences = [");
        stringBuilder.append("{id:");
        stringBuilder.append(getId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{Name:");
        stringBuilder.append(getName() != null ? getName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{maxSec:");
        stringBuilder.append(getMaxSec());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bornSec:");
        stringBuilder.append(getBornSec());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{color:");
        stringBuilder.append(getcolor() != null ? getcolor() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp2:");
        stringBuilder.append(getTmp2() != null ? getTmp2() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp3:");
        stringBuilder.append(getTmp3() != null ? getTmp3() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp4:");
        stringBuilder.append(getTmp4() != null ? getTmp4() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp5:");
        stringBuilder.append(getTmp5() != null ? getTmp5() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp6:");
        stringBuilder.append(getTmp6() != null ? getTmp6() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp7:");
        stringBuilder.append(getTmp7() != null ? getTmp7() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tmp8:");
        stringBuilder.append(getTmp8() != null ? getTmp8() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        userPerferencesRealmProxy auserPerferences = (userPerferencesRealmProxy)o;

        String path = realm.getPath();
        String otherPath = auserPerferences.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = auserPerferences.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != auserPerferences.row.getIndex()) return false;

        return true;
    }

}
