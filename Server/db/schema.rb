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

ActiveRecord::Schema.define(:version => 20120522141855) do

  create_table "colors", :force => true do |t|
    t.string   "hex"
    t.integer  "user_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "colors", ["user_id"], :name => "index_colors_on_user_id"

  create_table "draws", :force => true do |t|
    t.string   "id_creator"
    t.string   "latitude"
    t.string   "longitude"
    t.text     "draw"
    t.boolean  "challenge"
    t.text     "description"
    t.string   "password"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  create_table "users", :force => true do |t|
    t.string   "email"
    t.string   "name"
    t.integer  "id_avatar"
    t.integer  "piggies"
    t.integer  "keys"
    t.integer  "num_done"
    t.integer  "num_success"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

end