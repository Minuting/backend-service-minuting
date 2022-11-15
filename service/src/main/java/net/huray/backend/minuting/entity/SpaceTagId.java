package net.huray.backend.minuting.entity;


import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpaceTagId implements Serializable  {

        private Space space;
        private Tag tag;

        @Override
        public boolean equals(Object o) {
                if (this == o) {
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        return false;
                }
                SpaceTagId that = (SpaceTagId) o;
                return Objects.equals(space, that.space) && Objects.equals(tag, that.tag);
        }

        @Override
        public int hashCode() {
                return Objects.hash(space, tag);
        }
}
