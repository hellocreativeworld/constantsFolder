package CPP.Absyn; // Java Package generated by the BNF Converter.

public class ADecl extends Arg {
  public final Type type_;
  public final String id_;
  public ADecl(Type p1, String p2) { type_ = p1; id_ = p2; }

  public <R,A> R accept(CPP.Absyn.Arg.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof CPP.Absyn.ADecl) {
      CPP.Absyn.ADecl x = (CPP.Absyn.ADecl)o;
      return this.type_.equals(x.type_) && this.id_.equals(x.id_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.type_.hashCode())+this.id_.hashCode();
  }


}
