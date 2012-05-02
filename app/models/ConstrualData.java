package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class ConstrualData extends Model {
	public static List<ConstrualData> findDataByType(String type) {
		return find("select d from ConstrualData d where MONTH(d.day) =? ", type).fetch();
	}
}
