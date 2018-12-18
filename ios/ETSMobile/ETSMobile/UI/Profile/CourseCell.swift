//
//  CourseCell.swift
//  ETSMobile
//
//  Created by Emmanuel Proulx on 2018-12-13.
//  Copyright © 2018 ApplETS. All rights reserved.
//

import UIKit

class CourseCell: UICollectionViewCell {
    
    @IBOutlet weak var courseTitle: UILabel!
    
    @IBOutlet weak var gradeTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.style()
        
    }
    
    func style() {
        self.backgroundColor = UIColor.clear
        self.layer.borderColor = UIColor.red.withAlphaComponent(0.7).cgColor
        self.layer.borderWidth = 1.0
        self.layer.cornerRadius = 8.0
        self.tintColor = UIColor.white
    }
    
    func displayContent(title: String, grade: String) {
        courseTitle.text = title
        gradeTitle.text = grade
    }
}
