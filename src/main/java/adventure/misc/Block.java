package adventure.misc;

public enum Block {
  GROUND1(13, "/adventure/tiles/floor/floor_2.png", false),
  GROUND2(16, "/adventure/tiles/floor/floor_side4.png", false),
  GROUND3(17, "/adventure/tiles/floor/floor_side.png", false),
  GROUND4(18, "/adventure/tiles/floor/floor_side5.png", false),
  GROUND5(16, "/adventure/tiles/floor/floor_side4.png", false),
  GROUND6(23, "/adventure/tiles/floor/floor_9.png", false),
  GROUND7(24, "/adventure/tiles/floor/floor_5.png", false),
  GROUND8(25, "/adventure/tiles/floor/floor_side3.png", false),
  GROUND9(27, "/adventure/tiles/floor/floor_side2.png", false),
  GROUND10(28, "/adventure/tiles/floor/floor_stair.png", false),
  GROUND11(33, "/adventure/tiles/floor/floor_10.png", false),

  WALL1(40,"/adventure/tiles/wall/wall_top_inner_right.png",true),
  WALL2(41,"/adventure/tiles/wall/wall_top_inner_left.png",true),
  WALL3(43, "/adventure/tiles/wall/wall_side_left.png", true),
  WALL4(44, "/adventure/tiles/wall/wall_1.png", true),
  WALL5(45, "/adventure/tiles/wall/wall_side_right.png", true),
  WALL6(49,"/adventure/tiles/wall/wall_top_right.png",true),
  WALL7(50, "/adventure/tiles/wall/wall_top_left.png", true),
  WALL8(52, "/adventure/tiles/wall/wall_2.png", true),
  WALL9(54, "/adventure/tiles/wall/wall_crack.png", true),
  WALL10(58,"/adventure/tiles/wall/wall_bottom_inner_right.png",true),
  WALL11(59, "/adventure/tiles/wall/wall_bottom_inner_left.png", true),
  WALL12(65,"/adventure/tiles/wall/wall_top_inner_right_2.png",true),
  WALL13(66,"/adventure/tiles/wall/wall_top_1.png",true),
  WALL14(69,"/adventure/tiles/wall/wall_top_inner_left_2.png",true),
  WALL15(62,"/adventure/tiles/wall/wall_arch_left.png",false),
  WALL16(63,"/adventure/tiles/wall/wall_arch_right.png",false),

  DECOR1(15,"/adventure/tiles/decoration/table.png",true),
  DECOR2(2,"/adventure/tiles/decoration/barrel.png",true),
  DECOR3(5,"/adventure/tiles/decoration/flag_red.png",true),
  DECOR4(4,"/adventure/tiles/decoration/flag_green.png",true),
  DECOR5(3,"/adventure/tiles/decoration/bookshelf.png",true),
  DECOR6(14,"/adventure/tiles/decoration/prisoner.png",true),
  DECOR7(163,"/adventure/tiles/decoration/torch_anim_f0.png",true),
  DECOR8(10,"/adventure/tiles/decoration/table_2_left.png",true),
  DECOR9(11,"/adventure/tiles/decoration/table_2_right.png",true);

  public int id;
  public String path;
  public boolean collidable;

  Block(int id, String path, boolean collidable) {
    this.id = id;
    this.path = path;
    this.collidable = collidable;
  }

  public static Block fromId(int id) {
    for (Block block : Block.values()) {
      if (block.id == id) {
        return block;
      }
    }
    return null;
  }
}