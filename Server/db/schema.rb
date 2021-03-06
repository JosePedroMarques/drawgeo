# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20120602222052) do

  create_table "avatars", :force => true do |t|
    t.string   "url"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "draw_users", :force => true do |t|
    t.integer  "id_draw"
    t.integer  "id_user"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "draws", :force => true do |t|
    t.float    "latitude"
    t.float    "longitude"
    t.text     "draw"
    t.boolean  "challenge"
    t.text     "description"
    t.string   "password"
    t.datetime "created_at",                   :null => false
    t.datetime "updated_at",                   :null => false
    t.integer  "word_id"
    t.integer  "id_creator"
    t.text     "drawx"
    t.text     "drawy"
    t.float    "xdensity",    :default => 0.0
    t.float    "ydensity",    :default => 0.0
  end

  create_table "palette_users", :force => true do |t|
    t.integer  "id_user"
    t.integer  "id_palette"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "palettes", :force => true do |t|
    t.string   "hex1"
    t.string   "hex2"
    t.string   "hex3"
    t.string   "hex4"
    t.integer  "cost"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "users", :force => true do |t|
    t.string   "email"
    t.string   "name"
    t.integer  "id_avatar"
    t.integer  "piggies"
    t.integer  "keys"
    t.integer  "num_done"
    t.integer  "num_success"
    t.datetime "created_at",                 :null => false
    t.datetime "updated_at",                 :null => false
    t.integer  "ranking"
    t.integer  "num_created", :default => 0
  end

  create_table "words", :force => true do |t|
    t.string   "word"
    t.integer  "difficulty"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

end
