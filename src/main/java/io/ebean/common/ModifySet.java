package io.ebean.common;

import io.ebean.bean.BeanCollection;

import java.util.Set;

/**
 * Wraps a Set for the purposes of notifying removals and additions to the
 * BeanCollection owner.
 * <p>
 * This is required for persisting ManyToMany objects. Additions and removals
 * become inserts and deletes to the intersection table.
 * </p>
 */
class ModifySet<E> extends ModifyCollection<E> implements Set<E> {

  /**
   * Create with an Owner that is notified of any additions or deletions.
   */
  public ModifySet(BeanCollection<E> owner, Set<E> s) {
    super(owner, s);
  }

}
